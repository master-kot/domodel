package ru.geekbrains.domodel.news.services.api;

import ru.geekbrains.domodel.news.entities.News;

import java.util.List;

/**
 * Сервис новостей
 */
public interface NewsService {

    /**
     * Получить список новостей
     *
     * @return список новостей
     */
    List<News> getAllNews();

    /**
     * Получить новость по ее идентификатору
     *
     * @param id идентификатор новости
     * @return новость
     */
    News getNewsById(Long id);

    /**
     * Удалить новость по ее идентификатору
     *
     * @param id
     */
    void deleteNewsById(Long id);

    /**
     * Сохранить новость
     *
     * @param news
     * @return
     */
    public News saveNews (News news);

    /**
     * Изменить новость
     *
     * @param id
     * @param shortText
     * @param text
     * @param active
     * @return
     */
    News changeNews (Long id, String shortText, String text, boolean active);

}
