package ru.geekbrains.domodel.news.services.api;

import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.news.entities.News;

import java.util.List;

/**
 * Сервис новостей
 */
@Service
public interface NewsService {

    /**
     * Получить список новостей
     *
     * @return
     */
    List<News> getAllNews();

    /**
     *
     *
     * @param id
     * @return
     */
    News getNewsById(Long id);

    /**
     *
     *
     * @param title
     * @return
     */
    News getNewsByTitle(String title);

    /**
     *
     *
     * @param id
     */
    void deleteNewsById(Long id);

    /**
     *
     *
     * @param news
     * @return
     */
    public News saveNews (News news);

    /**
     *
     *
     * @param id
     * @param shortText
     * @param text
     * @param active
     * @return
     */
    News changeNews (Long id, String shortText, String text, boolean active);

}
