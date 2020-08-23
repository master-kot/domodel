package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.repositories.NewsRepository;
import ru.geekbrains.domodel.services.api.NewsService;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса новостей
 */
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    // Репозиторий новостей
    private final NewsRepository newsRepository;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News getNewsById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        return news.orElse(null);
    }

    @Override
    public void deleteNewsById(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public News saveNews (News news) {
        return newsRepository.save(news);
    }

    @Override
    public News changeNews (Long id,
                            String title,
                            String fullText,
                            boolean hidden,
                            boolean pinned,
                            String pictureLink) {
        News news = newsRepository.getOne(id);
        if (news != null) {
            news.setTitle(title);
            news.setFullText(fullText);
            news.setHidden(hidden);
            news.setPinned(pinned);
            news.setPictureLink(pictureLink);
            return newsRepository.save(news);
        }
        return null;
    }

    // TODO реализовать метод
    @Override
    public News getLastNews() {
        List<News> newsList = newsRepository.findAll();
        if (!newsList.isEmpty()) {
            return newsList.get(newsList.size() - 1);
        }
        return null;
    }
}