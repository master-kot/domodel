package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.InformationDto;

/**
 * Интерфейс сервиса информации о компании
 */
public interface InformationService {

    /**
     * Получить блок информацию по индексу
     */
    InformationDto getInformationById(Integer id);
}
