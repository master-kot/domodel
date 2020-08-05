package ru.geekbrains.domodel.news.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.news.entities.News;
import ru.geekbrains.domodel.news.repositories.NewsRepository;
import ru.geekbrains.domodel.news.services.api.NewsService;

import java.util.List;

/**
 * Сервис новостей
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
        return newsRepository.findById(id).get();
    }

    @Override
    public News getNewsByTitle(String title) {
        return newsRepository.findOneByTitle(title);
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
    public News changeNews (Long id, String shortText, String text, boolean active) {
        News news = newsRepository.getOne(id);
        if (news != null) {
            news.setShortText(shortText);
            news.setText(text);
            news.setActive(active);
            return newsRepository.save(news);
        }
        return null;
    }
}