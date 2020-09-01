package ru.geekbrains.domodel.services.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.entities.User;

import java.util.Date;
import java.util.List;

/**
 * Интерфейс сервиса новостей
 */
public interface NewsService {

    //ЧТЕНИЕ
    /**
     * Получить новость по ее идентификатору
     * @param id идентификатор новости
     * @return новость
     */
    NewsDto readNewsById(Long id);

    /**
     * Получить список архива новостей
     * @return список новостей
     */
    List<NewsDto> readNewsArchive();

    /**
     * Получить список актуальных новостей для главной страницы
     * @return список новостей
     */
    List<NewsDto> readRelevantNews();



    //РЕДАКТИРОВАНИЕ
    /**
     * Cоздать новость
     * @param newsDto данные с фронта
     * @return новость, сохраненная в репозитории
     */
    NewsDto createNews  (NewsDto newsDto);

    /**
     * Изменить новость
     * @param id идентификатор новости
     * @return измененная новость
     */
    NewsDto updateNewsById (Long id, NewsDto newsDto);

    /**
     *Закрепляем выбранную новость
     *@param id идентификатор новости
     *@param pinned флажок закреплена ли новость true - закреплена
     */
    NewsDto updatePinningNewsById(Long id, boolean pinned);

    /**
     * изменение видимости новости
     * @param id идентификатор новости
     * @param visible флажок видимости новости
     */
    NewsDto updateVisibilityNewsById(Long id , boolean visible);


    /**
     * Сохранить новость
     * @param news данные с фронта
     * @return новость, сохраненная в репозитории
     */
    News saveNews(News news);
    NewsDto saveNews(NewsDto news);

    // Page<News> findAll(Pageable pageable);

    /**
     * Получить список всех новостей
     * @return список новостей
     */
    List<News> readAllNews();

        /**
     * Получить последнюю по дате новость
     * @return новость
     */
    News readLastNews();
//
//    /**
//     * Получить список видимых новостей для зарегистрированных пользователей + вначале закрепленные новости + пагинация
//     * @return список новостей
//     */
//    List<News> readAllVisibleNews();
//
//    /**
//     * Получить список новостей для незарегистрированных пользователей + вначале закрепленные новости + пагинация
//     * @return список новостей
//     */
//    List<News> readPublicNews();
//
//    /**
//     * Получить список закрепленных новостей (не более 2)
//     * @return список новостей
//     */
//    List<News> readPinnedNews();
}

