package ru.geekbrains.domodel.services.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.dto.NewsRequestDto;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.entities.User;

import java.util.Date;
import java.util.List;

/**
 * Интерфейс сервиса новостей
 */
public interface NewsService {

    /*
     * СОГЛАШЕНИЕ О НАИМЕНОВАНИИ МЕТОДОВ СЕРВИСОВ
     * NewsDto getNewsById(Long id) найти объект по параметру
     * List<NewsDto> getAllNews() найти все объекты
     * List<NewsDto> getAllNewsByUser(UserDto user) найти все объекты по параметру
     * News updateNews(NewsDto news) изменить объект
     * News saveNews(NewsDto newsDto) сохранить объект
     * List<NewsDto> saveAllNews(List<NewsDto> newsDtoList) сохранить список объектов
     * void deleteNews(NewsDto newsDto) удалить конкретный объект
     * Long deleteNewsById(Long id) удалить объект по параметру
     * void deleteAllNews(List<NewsDto> newsDtoList) удалить список объектов
     */

    /**
     * Получить список новостей
     *
     * @return список новостей
     */
    List<NewsDto> getAll();

    /**
     * Получить новость по ее идентификатору
     *
     * @param id идентификатор новости
     * @return новость
     */
    NewsDto getById(Long id, Authentication authentication);

    /**
     * Получить список архива новостей
     * @return список новостей
     */
    List<NewsDto> getArchive(int page, Authentication authentication);

    /**
     * Получить список актуальных новостей для главной страницы
     * @return список новостей
     */
    List<NewsDto> getAllRelevant(Authentication authentication);

    /**
     * Изменить новость
     * @param id идентификатор новости
     * @return измененная новость
     */
    NewsDto updateNewsById (Long id, NewsDto newsDto, Authentication authentication);

    /**
     *Закрепляем выбранную новость
     *@param id идентификатор новости
     *@param pinned флажок закреплена ли новость true - закреплена
     */
    boolean updatePinningNewsById(Long id, boolean pinned, Authentication authentication);

    /**
     * изменение видимости новости
     * @param id идентификатор новости
     * @param visible флажок видимости новости
     */
    boolean updateVisibilityNewsById(Long id , boolean visible, Authentication authentication);

    /**
     * Сохраняет новость с учетом имени автора
     *
     * @param newsRequestDto запрос на создание новости
     * @return новость, сохраненная в репозитории
     */
    NewsDto save(NewsRequestDto newsRequestDto, Authentication authentication);

    // Page<News> findAll(Pageable pageable);

        /**
     * Получить последнюю по дате новость
     * @return новость
     */
    News getLast();
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
