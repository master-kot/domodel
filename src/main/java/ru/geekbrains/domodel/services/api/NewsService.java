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
    
    /**
     * Выдает новость по ее идентификатору
     *
     * @param id идентификатор новости
     * @return новость
     */
    NewsDto getById(Long id, Authentication authentication);
    
    /**
     * Выдает список всех новостей
     *
     * @return список новостей
     */
    List<NewsDto> getAll();

    /**
     * Выдает список новостей по определенной странице архива
     *
     * @return список новостей
     */
    List<NewsDto> getArchive(int page, Authentication authentication);

    /**
     * Отдает список актуальных новостей для главной страницы
     *
     * @return список новостей
     */
    List<NewsDto> getAllRelevant(Authentication authentication);

    /**
     * Изменяет новость
     *
     * @param id идентификатор новости
     * @return измененная новость
     */
    NewsDto updateNewsById (Long id, NewsDto newsDto, Authentication authentication);

    /**
     *Изменяет параметр закрепления новости
     *
     *@param id идентификатор новости
     *@param pinned закреплена ли новость
     */
    boolean updatePinningNewsById(Long id, boolean pinned, Authentication authentication);

    /**
     * Изменяет параметр видимости новости
     *
     * @param id идентификатор новости
     * @param visible флажок видимости новости
     */
    boolean updateVisibilityNewsById(Long id , boolean visible, Authentication authentication);

    /**
     * Сохраняет новость
     *
     * @param newsRequestDto запрос на создание новости
     * @return новость, сохраненная в репозитории
     */
    NewsDto save(NewsRequestDto newsRequestDto, Authentication authentication);
}
