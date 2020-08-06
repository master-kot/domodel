package ru.geekbrains.domodel.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.repositories.NewsRepository;
import ru.geekbrains.domodel.services.api.NewsService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса новостей
 */
@Service
public class NewsServiceImpl implements NewsService {

    // Репозиторий новостей
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

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
    public News changeNews (Long id, Date creationDate, String shortText, String text, boolean visible, String picture) {
        News news = newsRepository.getOne(id);
        if (news != null) {
            news.setShortText(shortText);
            news.setText(text);
            news.setVisible(visible);
            news.setPicture(picture);
            return newsRepository.save(news);
        }
        return null;
    }
}