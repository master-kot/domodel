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
       //newsRepository.deleteById(id);
        //удаленная новость сохраняется в БД
        getNewsById(id).setVisible(false);
    }

    // TODO через POST
    @Override
    public News saveNews (News news) {
        //TODO прописсать поля, сооздающиеся автоматически (дата, автор, visible); учесть, что закрепленных может быть только 2
        return newsRepository.save(news);
    }

    // TODO через POST
    @Override
    public News changeNews (Long id,
                            String title,
                            String fullText,
                            boolean hidden,
                            boolean pinned,
                            boolean visible, //можно через редактирование восстановить удаленную новость
                            String pictureLink) {
        News news = newsRepository.getOne(id);
        if (news != null) {
            news.setTitle(title);
            news.setFullText(fullText);
            news.setHidden(hidden);
            news.setPinned(pinned);
            news.setVisible(visible);
            news.setPictureLink(pictureLink);
            return newsRepository.save(news);
        }
        return null;
    }

    // TODO реализовать метод получение последней новости (убрать этот метод из главного контроллера)
    @Override
    public News getLastNews() {
        List<News> newsList = newsRepository.findAll();
        if (!newsList.isEmpty()) {
            return newsList.get(newsList.size() - 1);
        }
        return null;
    }

    // TODO реализовать метод получения списка актуальных новвостей + в начале закрепленные (не более 2) + пагинация
    @Override
    public List<News> getAllVisibleNews() {
        return null;
    }

    // TODO реализовать метод получения списка закрепленных новостей (2 штуки)
    @Override
    public List<News> getPinnedNews() {return null;}

    // TODO реализовать метод получения списка новостей для незарегистрированных пользователей + пагинация
    @Override
    public List<News> getPublicNews() {return null;}

}