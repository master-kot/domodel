package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.dto.NewsRequestDto;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.mappers.NewsMapper;
import ru.geekbrains.domodel.repositories.NewsRepository;
import ru.geekbrains.domodel.services.api.NewsService;
import ru.geekbrains.domodel.services.api.UserService;

import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;

/**
 * Реализация сервиса новостей
 */
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    // TODO исправить методы на стримы
    private final UserService userService;
    private final NewsMapper newsMapper;

    // Репозиторий новостей
    private final NewsRepository newsRepository;

    @Override
    public NewsDto getById(Long id, Authentication authentication) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (hasAuthenticationRoleAdmin(authentication) || authentication!=null&&optionalNews.get().isVisible()||authentication==null&&!optionalNews.get().isHidden()&&optionalNews.get().isVisible())
            return optionalNews.map(newsMapper::newsToNewsDto).orElse(null);
        else return null;
    }

    @Override
    //Архив новостей
    public List<NewsDto> getArchive(int page, Authentication authentication) {
        List<NewsDto> newsDtoList = new ArrayList<>();
        if (!authentication.isAuthenticated()) return newsDtoList;
        List<News> newsArchive = getAllVisibleNews();
        if (hasAuthenticationRoleAdmin(authentication)) newsArchive.addAll(getDeletedNews());
        for (int i = (page-1)*10; i<page*10 && i < newsArchive.size(); i++) {
            newsDtoList.add(newsMapper.newsToNewsDto(newsArchive.get(i)));
        }
        return newsDtoList;
    }

    @Override
    public List<NewsDto> getAllRelevant(Authentication authentication) {
//        Stream<NewsDto> newsDtoStream = newsRepository.findAll().stream().map(newsMapper::newsToNewsDto);
//        // Если пользователь не авторизован
//        if (authentication == null) {
//            return newsDtoStream.filter(n -> !n.isHidden() && !n.isVisible()).collect(Collectors.toList());
//        }
//        // Если пользователь - админ
//        if (hasAuthenticationRoleAdmin(authentication)) {
////            return newsDtoStream.limit(10).collect(Collectors.toList());
//        } else { // Просто пользователь
////            return newsDtoStream.filter(n -> !n.isHidden()).collect(Collectors.toList());
//        }

        List<News> allNews = new ArrayList<News>();
        List<NewsDto> newsRelevant = new ArrayList<>();
        if (authentication == null) allNews = getPublicNews();
        else allNews = getAllVisibleNews();
        int limit = (allNews.size() < 10) ? allNews.size() : 10;
        for (int i = 0; i < limit; i++) {
            newsRelevant.add(newsMapper.newsToNewsDto(allNews.get(i)));
        }
        return newsRelevant;
//        return allNews.stream().map(newsMapper::newsToNewsDto).collect(Collectors.toList());
    }

    // РЕДАКТИРОВАНИЕ
    @Override
    public boolean updateVisibilityNewsById(Long id, boolean visible, Authentication authentication) {
        //удаляем новость, меняя ее видимость и удаляя ее из закрепленных
        News news = newsRepository.getOne(id);
        if (news.isVisible() == visible || authentication == null || !hasAuthenticationRoleAdmin(authentication))
            return visible;
        else {
            news.setVisible(visible);
            newsRepository.save(news);
        }
        return visible;
    }

    @Override
    public NewsDto updateNewsById(Long id, NewsDto newsDto, Authentication authentication) {
        if (authentication == null || !hasAuthenticationRoleAdmin(authentication)) return null;
        News news = newsRepository.getOne(id);
        news.setCreationDate(LocalDate.now());
        news.setTitle(newsDto.getTitle());
        news.setFullText(newsDto.getFullText());
        news.setPictureLink(newsDto.getPictureLink());
        news.setHidden(newsDto.isHidden());
        if (news.isPinned()!=newsDto.isPinned()&&newsDto.isPinned()) {
            List<News> pinnedNews = getPinnedNews();
            if (pinnedNews.size() > 1) {
                pinnedNews.get(1).setPinned(false);
                newsRepository.save(pinnedNews.get(1));
            }
        }
        news.setPinned(newsDto.isPinned());
        news.setVisible(newsDto.isVisible());
        newsRepository.save(news);
        NewsDto newNewsDto = getById(id, authentication);
        return newNewsDto;
    }

    @Override
    public boolean updatePinningNewsById(Long id, boolean pinned, Authentication authentication) {
        //Закрепляем выбранную новость,если закрепленных уже 2, то одну открепляем
        News news = newsRepository.getOne(id);
        if (news.isPinned() == pinned || authentication == null || !hasAuthenticationRoleAdmin(authentication))
            return pinned;
        else {
            List<News> pinnedNews = getPinnedNews();
            if (pinnedNews.size() > 1 && pinned) {
                pinnedNews.get(1).setPinned(false);
                newsRepository.save(pinnedNews.get(1));
            }
            news.setPinned(pinned);
            newsRepository.save(news);
        }
        return pinned;
    }


    //ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ
    //ЧТЕНИЕ

    @Override
    public NewsDto save(NewsRequestDto newsRequestDto, Authentication authentication) {
        // Если пользователь не авторизован или не админ
        if (authentication == null || !hasAuthenticationRoleAdmin(authentication)) {
            return null;
        }
        News news = newsMapper.newsRequestDtoToNews(newsRequestDto);
        // Добавляем дату
        news.setCreationDate(LocalDate.now());
        // Добавляем автора новости
        news.setAuthorId(userService.getUserByUsername(authentication.getName()));
        return newsMapper.newsToNewsDto(newsRepository.save(news));
    }

    //получение поcледней новости
    @Override
    public News getLast() {
        List<News> newsList = getAllNews();
        if (!newsList.isEmpty()) {
            return newsList.get(0);
        }
        return null;
    }

    @Override
    public List<NewsDto> getAll() {
        List<News> newsList = newsRepository.findAll();
        Collections.reverse(newsList);
        return newsList.stream()
                .map(newsMapper::newsToNewsDto)
                .collect(Collectors.toList());
    }

    public List<News> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        Collections.reverse(newsList);
        return newsList;
    }


    //РЕДАКТИРОВАНИЕ

    private List<News> getAllVisibleNews() {
        //список неудаленных новостей для зарегистрированных пользователей. Сначала закрепленные, потом остальные по дате от новых к старым
        List<News> allNewsList = getAllNews();
        List<News> pinnedNewsList = getPinnedNews();
        List<News> newsList = new ArrayList<News>();
        newsList.addAll(getPinnedNews());
        for (int i = 0; i < allNewsList.size(); i++) { //добавляем остальные неудаленные новости
            if (allNewsList.get(i).isVisible() && !allNewsList.get(i).isPinned()) {
                newsList.add(allNewsList.get(i));
            }
        }
        return newsList;
    }

    private List<News> getDeletedNews() {
        //список удаленных новостей
        List<News> allNewsList = getAllNews();
        List<News> newsList = new ArrayList<News>();
        for (int i = 0; i < allNewsList.size(); i++) {
            if (!allNewsList.get(i).isVisible()) {
                newsList.add(allNewsList.get(i));
            }
        }
        return newsList;
    }

    private List<News> getPinnedNews() {
        //возвращает лист из 2 закрепленных новостей
        List<News> allNewsList = getAllNews();
        List<News> newsList = new ArrayList<>();
        byte counter = 0;
        for (int i = 0; i < allNewsList.size(); i++) {
            if (allNewsList.get(i).isPinned()) {
                newsList.add(allNewsList.get(i));
                counter++;
            }
            if (counter == 2) break;
        }
        return newsList;
    }

    private List<News> getPublicNews() {
        //список новостей (10 штук) для незарегистрированных пользователей. Сначала закрепленные, потом остальные по дате от новых к старым
        List<News> allNewsList = getAllNews();
        List<News> pinnedNewsList = getPinnedNews();
        List<News> newsList = new ArrayList<>();
        byte counter = 0;
        for (int i = 0; i < pinnedNewsList.size(); i++) { //добавляем закрепленные, если они публичные
            if (!pinnedNewsList.get(i).isHidden()) {
                newsList.add(pinnedNewsList.get(i));
                counter++;
            }
        }
        for (int i = 0; i < allNewsList.size(); i++) { //добавляем обычные новости до 10, если они видимы, публичны и незакреплены
            if (!allNewsList.get(i).isHidden() && allNewsList.get(i).isVisible() && !allNewsList.get(i).isPinned()) {
                newsList.add(allNewsList.get(i));
                counter++;
            }
            if (counter == 10) break;
        }
        return newsList;
    }

    /**
     * Проверить, что пользователь имеет роль Админа
     */
    private boolean hasAuthenticationRoleAdmin(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_ADMIN)));
    }
}