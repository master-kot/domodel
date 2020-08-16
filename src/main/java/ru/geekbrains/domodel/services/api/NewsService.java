package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.News;

import java.util.Date;
import java.util.List;

/**
 * Интерфейс сервиса новостей
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
     * @param id идентификатор новости
     */
    void deleteNewsById(Long id);

    /**
     * Сохранить новость
     *
     * @param news новость, отдаваемая в репозиторий для сохранения
     * @return новость, сохраненная в репозитории
     */
    public News saveNews (News news);

    /**
     * Изменить новость
     *
     * @param id идентификатор новости
     * @param shortText краткий текст новости
     * @param text полный текст новости
     * @param visible видима ли новость
     * @return изменить новость
     */
    News changeNews (Long id, Date creationDate, String shortText, String text, boolean visible, String picture);

    /**
     * Получить последнюю по дате новость
     *
     * @return новость
     */
    News getLastNews();
}
