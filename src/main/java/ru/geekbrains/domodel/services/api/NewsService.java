package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.NewsDto;

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
    List<NewsDto> getAllNews();

    /**
     * Получить новость по ее идентификатору
     *
     * @param id идентификатор новости
     * @return новость
     */
    NewsDto getNewsById(Long id);

    /**
     * Удалить новость по ее идентификатору
     *
     * @param id идентификатор новости
     */
    void deleteNewsById(Long id);

    /**
     * Сохранить новость
     *
     * @param newsDto новость, отдаваемая для сохранения
     * @return новость, сохраненная в репозитории
     */
    NewsDto saveNews (NewsDto newsDto);

    /**
     * Изменить новость
     *
     * @param id идентификатор новости
     * @param title заголовок новости
     * @param fullText полный текст новости
     * @param hidden видима ли новость
     * @param pinned закреплена ли новость
     * @param pictureLink ссылка на картинку новости
     * @return измененная новость
     */
    NewsDto updateNews(Long id,
                    String title,
                    String fullText,
                    boolean hidden,
                    boolean pinned,
                    String pictureLink);

    /**
     * Получить список актуальных новостей
     *
     * @return список новостей
     */
    List<NewsDto> getRelevantNews();
}
