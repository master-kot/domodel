package ru.geekbrains.domodel.services.api;

import org.springframework.security.core.Authentication;
import ru.geekbrains.domodel.dto.VoteDto;

/**
 * Интерфейс сервиса голосований
 */
public interface VoteService {

    /*
     * СОГЛАШЕНИЕ О НАИМЕНОВАНИИ МЕТОДОВ СЕРВИСОВ
     * News getById(Long id) найти объект по параметру
     * NewsDto getDtoById(Long id) найти Dto объект по параметру
     * Collection<News> getAll() найти все объекты
     * Collection<NewsDto> getAllDto() найти все Dto объекты
     * Collection<NewsDto> getAllDtoByUser(UserDto userDto) найти все Dto объекты по параметру
     * NewsDto update(NewsDto newsDto) изменить объект
     * NewsDto save(NewsDto newsDto) сохранить объект
     * Collection<NewsDto> saveAllDto(Collection<NewsDto> newsDtoCollection) сохранить список объектов
     * void delete(NewsDto newsDto) удалить конкретный объект
     * void deleteById(Long id) удалить объект по параметру
     * void deleteAll(Collection<NewsDto> newsDtoCollection) удалить список объектов
     */

    /**
     * Получить голосование по индексу
     */
    VoteDto getDtoById(Long id, Authentication authentication);
}
