package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.mappers.NewsMapper;
import ru.geekbrains.domodel.repositories.NewsRepository;
import ru.geekbrains.domodel.services.api.NewsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Реализация сервиса новостей
 */
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    // Репозиторий новостей
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

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

    @Override
    public NewsDto saveNews (NewsDto newsDto) {
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

    // TODO реализовать метод
    @Override
    public NewsDto getLastNews() {
        return null;
    }

    // TODO реализовать метод
    @Override
    public List<NewsDto> getRelevantNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::newsToNewsDto)
                .collect(Collectors.toList());
    }
}
