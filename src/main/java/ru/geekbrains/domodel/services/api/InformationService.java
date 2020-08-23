package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Information;

import java.util.Optional;

/**
 * Интерфейс сервиса информации о компании
 */
public interface InformationService {

    /**
     * Получить информацию по индексу
     */
    Optional<Information> getById(Integer id);
}
