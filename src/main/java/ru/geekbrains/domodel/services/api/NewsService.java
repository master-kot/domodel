package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.entities.User;

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
     * Cоздать новость
     *

     * @param title заголовок новости
     * @param fullText полный текст новости
     * @param hidden видима ли новость
     * @param pinned закреплена ли новость
     * @param pictureLink ссылка на картинку новости
     * @return новость, сохраненная в репозитории
     */
    News createNews  (String title,
                      String fullText,
                      boolean hidden,
                      boolean pinned,
                      String pictureLink,
                      User user);

    /**
     * Изменить новость
     *
     * @param id идентификатор новости
     * @param title заголовок новости
     * @param fullText полный текст новости
     * @param hidden видима ли новость
     * @param pinned закреплена ли новость
     * @param visible актуальна ли новость
     * @param pictureLink ссылка на картинку новости
     * @return измененная новость
     */
    News changeNews (Long id,
                     String title,
                     String fullText,
                     boolean hidden,
                     boolean pinned,
                     boolean visible,
                     String pictureLink);

    /**
     * Получить последнюю по дате новость
     *
     * @return новость
     */
    News getLastNews();

    /**
     * Получить список видимых новостей для зарегистрированных пользователей + вначале закрепленные новости + пагинация
     *
     * @return список новостей
     */
    List<News> getAllVisibleNews();

    /**
     * Получить список новостей для незарегистрированных пользователей + вначале закрепленные новости + пагинация
     *
     * @return список новостей
     */
    List<News> getPublicNews();

    /**
     * Получить список закрепленных новостей (не более 2)
     *
     * @return список новостей
     */
    List<News> getPinnedNews();

    /**
     *Закрепляем выбранную новость
     *
     */
    void toPinnedNews(News news);

}
