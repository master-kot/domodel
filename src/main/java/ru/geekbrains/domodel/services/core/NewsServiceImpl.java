package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.mappers.NewsMapper;
import ru.geekbrains.domodel.repositories.NewsRepository;
import ru.geekbrains.domodel.services.api.NewsService;
import ru.geekbrains.domodel.services.api.UserService;

import java.time.LocalDate;
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

    private final NewsMapper newsMapper;
    private final UserService userService;

    // Репозиторий новостей
    private final NewsRepository newsRepository;

    @Override
    public List<NewsDto> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::newsToNewsDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto getNewsById(Long id) {
        Optional<News> optionalNews = newsRepository.findById(id);
        return optionalNews.map(newsMapper::newsToNewsDto).orElse(null);
    }

    @Override
    public void deleteNewsById(Long id) {
        newsRepository.deleteById(id);
    }

    public NewsDto save (NewsDto newsDto) {
        News news = newsMapper.newsDtoToNews(newsDto);
        return newsMapper.newsToNewsDto(newsRepository.save(news));
    }

    @Override
    public NewsDto updateNews(Long id,
                           String title,
                           String fullText,
                           boolean hidden,
                           boolean pinned,
                           String pictureLink) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            news.setTitle(title);
            news.setFullText(fullText);
            news.setHidden(hidden);
            news.setPinned(pinned);
            news.setPictureLink(pictureLink);
            return newsMapper.newsToNewsDto(newsRepository.save(news));
        }
        return null;
    }

    // TODO реализовать метод основываясь на правильных запросах в репозиторий
    @Override
    public List<NewsDto> getRelevantNews(Authentication authentication) {
        Stream<NewsDto> newsDtoStream = newsRepository.findAll().stream().map(newsMapper::newsToNewsDto);
        // Если пользователь не авторизован
        if (authentication == null) {
            return newsDtoStream.filter(n -> !n.isHidden() && !n.isVisible()).collect(Collectors.toList());
        }
        // Если пользователь - админ
        if (hasAuthenticationRoleAdmin(authentication)) {
            return newsDtoStream.limit(10).collect(Collectors.toList());
        } else { // Просто пользователь
            return newsDtoStream.filter(n -> !n.isHidden()).collect(Collectors.toList());
        }
    }

    @Override
    public NewsDto save(NewsDto newsDto, Authentication authentication) {
        // Если пользователь не авторизован или не админ
        if (authentication == null || !hasAuthenticationRoleAdmin(authentication)) {
            return null;
        }
        News news = newsMapper.newsDtoToNews(newsDto);
        // Добавляем дату
        news.setCreationDate(LocalDate.now());
        // Добавляем автора новости
        news.setAuthorId(userService.getUserByUsername(authentication.getName()));
        return newsMapper.newsToNewsDto(newsRepository.save(news));
    }

    /**
     * Проверить, что пользователь имеет роль Админа
     */
    private boolean hasAuthenticationRoleAdmin(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_ADMIN)));
    }
}
