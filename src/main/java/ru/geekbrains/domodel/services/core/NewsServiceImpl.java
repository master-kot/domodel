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

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;

/**
 * Реализация сервиса новостей
 */
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

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
        List<NewsDto> newsArchive = getAllVisibleNews();
        if (hasAuthenticationRoleAdmin(authentication)) newsArchive.addAll(getDeletedNews());
        int limit = (newsArchive.size() < page*10+9) ? newsArchive.size() : page*10 + 9;
        return newsArchive.subList((page - 1) * 10, limit);
    }

    @Override
    public List<NewsDto> getRelevantDto(Authentication authentication) {
        List<NewsDto> allNews = getAllVisibleNews();
        int limit = (allNews.size() < 10) ? allNews.size() : 10;
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

        updatePinningById(id, newsDto.isPinned(), authentication);

        news.setVisible(newsDto.isVisible());
        newsRepository.save(news);
        return getDtoById(id, authentication);
    }

    @Override
    public boolean updatePinningById(Long id, boolean pinned, Authentication authentication) {
        //Закрепляем или открепляем выбранную новость
        News news = newsRepository.getOne(id);
        if (news.isPinned() == pinned || !hasAuthenticationRoleAdmin(authentication)||!news.isVisible())
            return pinned;
        else {
            if (pinned && getPinnedNews().isPresent()) {
                NewsDto pinnedNews = getPinnedNews().get();
                pinnedNews.setPinned(false);
                save(pinnedNews,authentication);
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
    public NewsDto save(NewsDto dto, Authentication authentication) {
        // Если пользователь не авторизован или не админ
        if (authentication == null || !hasAuthenticationRoleAdmin(authentication)) {
            return null;
        }
        News news = newsMapper.newsDtoToNews(dto);
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
        //список неудаленных новостей для зарегистрированных пользователей. Сначала закрепленная, потом остальные по дате от новых к старым
        List<NewsDto> newsList = new ArrayList<>();
        if (getPinnedNews().isPresent()) newsList.add(getPinnedNews().get());
        newsList.addAll(getAllDto().stream().filter(news -> news.isVisible()).filter(news -> !news.isPinned()).collect(Collectors.toList()));
        return newsList;
    }

    private List<NewsDto> getDeletedNews() {
        //список удаленных новостей
        return getAllNews().stream().filter(news -> !news.isVisible()).map(newsMapper::newsToNewsDto).collect(Collectors.toList());
    }

    private Optional<NewsDto> getPinnedNews() {
        Optional<NewsDto> news = newsRepository.findAll().stream().filter((n -> n.isPinned())).map(newsMapper::newsToNewsDto).findFirst();
        return news;
    }

    /**
     * Проверить, что пользователь имеет роль Админа
     */
    private boolean hasAuthenticationRoleAdmin(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_ADMIN)));
    }
}