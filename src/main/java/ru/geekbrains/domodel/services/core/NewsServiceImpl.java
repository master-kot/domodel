package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.dto.NewsRequest;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.mappers.NewsMapper;
import ru.geekbrains.domodel.repositories.NewsRepository;
import ru.geekbrains.domodel.services.api.NewsService;
import ru.geekbrains.domodel.services.api.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;

/**
 * Реализация сервиса новостей
 */
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    // TODO проверить работоспособность, после этого стереть лишнее
    private final UserService userService;
    private final NewsMapper newsMapper;

    // Репозиторий новостей
    private final NewsRepository newsRepository;

    @Override
    public NewsDto getDtoById(Long id, Authentication authentication) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (hasAuthenticationRoleAdmin(authentication) || authentication != null && optionalNews.get().isVisible())
            return optionalNews.map(newsMapper::newsToNewsDto).orElse(null);
        else return null;
    }

    @Override
    public List<NewsDto> getArchiveDtoByPageId(int page, Authentication authentication) {
//        List<NewsDto> newsDtoList = new ArrayList<>();
        List<NewsDto> newsArchive = getAllVisibleNews();
//        if (hasAuthenticationRoleAdmin(authentication)) newsArchive.addAll(getDeletedNews());
//        for (int i = (page - 1) * 10; i < page * 10 && i < newsArchive.size(); i++) {
//            newsDtoList.add(newsMapper.newsToNewsDto(newsArchive.get(i)));
//        }
//        return newsDtoList;
        return newsArchive.subList((page-1)*10, page*10);
    }

    @Override
    public List<NewsDto> getRelevantDto(Authentication authentication) {
//        Stream<NewsDto> newsDtoStream = newsRepository.findAll().stream().map(newsMapper::newsToNewsDto);
//        // Если пользователь не авторизован
//        if (authentication == null) {
//            return newsDtoStream.filter(n -> !n.isHidden() && !n.isVisible()).collect(Collectors.toList());
//        }
//        // Если пользователь - админ
//        if (hasAuthenticationRoleAdmin(authentication)) {
//            return newsDtoStream.limit(10).collect(Collectors.toList());
//        } else { // Просто пользователь
//            return newsDtoStream.filter(n -> !n.isHidden()).collect(Collectors.toList());
//        }

        List<NewsDto> allNews = getAllVisibleNews();
        int limit = (allNews.size() < 10) ? allNews.size() : 10;
//        List<NewsDto> newsRelevant = new ArrayList<>();
//        //if (authentication == null) allNews = getPublicNews();
//        for (int i = 0; i < limit; i++) {
//            newsRelevant.add(newsMapper.newsToNewsDto(allNews.get(i)));
//        }
        return allNews.subList(0, limit);
    }

    @Override
    public boolean updateVisibilityById(Long id, boolean visible, Authentication authentication) {
        //удаляем новость, меняя ее видимость и удаляя ее из закрепленных
        News news = newsRepository.getOne(id);
        if (news.isVisible() == visible || !hasAuthenticationRoleAdmin(authentication))
            return visible;
        else {
            news.setVisible(visible);
            news.setPinned(false);
            newsRepository.save(news);
        }
        return visible;
    }

    @Override
    public NewsDto updateById(Long id, NewsDto newsDto, Authentication authentication) {
        if (authentication == null || !hasAuthenticationRoleAdmin(authentication)) return null;
        News news = newsRepository.getOne(id);
        news.setCreationDate(LocalDate.now());
        news.setTitle(newsDto.getTitle());
        news.setFullText(newsDto.getFullText());
        news.setPictureLink(newsDto.getPictureLink());
        news.setHidden(false);
//        if (news.isPinned() != newsDto.isPinned() && newsDto.isPinned()) {
//            List<News> pinnedNews = getPinnedNews();
//            if (pinnedNews.size() > 1) {
//                pinnedNews.get(1).setPinned(false);
//                newsRepository.save(pinnedNews.get(1));
//            }
//        }
        updatePinningById(id, newsDto.isPinned(), authentication);
//        news.setPinned(newsDto.isPinned());
        news.setVisible(newsDto.isVisible());
        newsRepository.save(news);
        return getDtoById(id, authentication);
    }

    @Override
    public boolean updatePinningById(Long id, boolean pinned, Authentication authentication) {
        //Закрепляем или открепляем выбранную новость
        News news = newsRepository.getOne(id);
        if (news.isPinned() == pinned || !hasAuthenticationRoleAdmin(authentication))
            return pinned;
        else {
            if (pinned) {
                NewsDto pinnedNews = getPinnedNews();
                pinnedNews.setPinned(false);
                newsRepository.save(newsMapper.newsDtoToNews(pinnedNews));
            }
            news.setPinned(pinned);
            newsRepository.save(news);
        }
        return pinned;
    }

    @Override
    public NewsDto save(NewsRequest newsRequest, Authentication authentication) {
        // Если пользователь не авторизован или не админ
        if (authentication == null || !hasAuthenticationRoleAdmin(authentication)) {
            return null;
        }
        News news = newsMapper.newsRequestToNews(newsRequest);
        // Добавляем дату
        news.setCreationDate(LocalDate.now());
        // Добавляем автора новости
        news.setAuthor(userService.getByUsername(authentication.getName()));
        return newsMapper.newsToNewsDto(newsRepository.save(news));
    }

    @Override
    public List<NewsDto> getAllDto() {
        List<News> newsList = newsRepository.findAll();
        Collections.reverse(newsList);
        return newsList.stream()
                .map(newsMapper::newsToNewsDto)
                .sorted((n1, n2) -> n2.getCreationDate().compareTo(n1.getCreationDate()))
                .collect(Collectors.toList());
    }


    private List<News> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        Collections.reverse(newsList);
        return newsList;
    }

    private List<NewsDto> getAllVisibleNews() {
        //список неудаленных новостей для зарегистрированных пользователей. Сначала закрепленные, потом остальные по дате от новых к старым
//        List<News> allNewsList = getAllNews();
        List<NewsDto> newsList = new ArrayList<>();
        newsList.add(getPinnedNews());
        newsList.addAll(getAllDto().stream().filter(news -> !news.isVisible()).filter(news -> !news.isPinned()).collect(Collectors.toList()));
//        for (int i = 0; i < allNewsList.size(); i++) { //добавляем остальные неудаленные новости
//            if (allNewsList.get(i).isVisible() && !allNewsList.get(i).isPinned()) {
//                newsList.add(allNewsList.get(i));
//            }
//        }
        return newsList;
    }

    private List<NewsDto> getDeletedNews() {
        //список удаленных новостей
//        List<News> allNewsList = getAllNews();
//        List<News> newsList = new ArrayList<News>();
//        for (int i = 0; i < allNewsList.size(); i++) {
//            if (!allNewsList.get(i).isVisible()) {
//                newsList.add(allNewsList.get(i));
//            }
//        }
//        return newsList;
        return getAllNews().stream().filter(news -> !news.isVisible()).map(newsMapper::newsToNewsDto).collect(Collectors.toList());
    }

    private NewsDto getPinnedNews() {
        Optional<NewsDto> news = newsRepository.findAll().stream().filter((n -> n.isPinned())).map(newsMapper::newsToNewsDto).findAny();
        return news.get();
    }

//      private List<NewsDto> getRelevantNews(Authentication authentication) {
//        Stream<News> newsStream = newsRepository.findAll().stream()
//                .sorted((n1, n2) -> n2.getCreationDate().compareTo(n1.getCreationDate()))
//                .limit(10);
////        if (authentication == null) { // Если пользователь не авторизован
////            newsStream = newsStream.filter(n -> !n.isHidden() && n.isVisible());
////        }
//        // Одинаково для всех авторизованных пользователей
//            newsStream = newsStream.filter(News::isVisible);
//        return newsStream.sorted((n1, n2) -> n2.comparePinning(n1))
//                .map(newsMapper::newsToNewsDto)
//                .collect(Collectors.toList());
//      }

    /**
     * Проверить, что пользователь имеет роль Админа
     */
    private boolean hasAuthenticationRoleAdmin(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_ADMIN)));
    }
}