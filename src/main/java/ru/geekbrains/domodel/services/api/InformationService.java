package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.InformationDto;

import java.util.List;

/**
 * Интерфейс сервиса информации о компании
 */
public interface InformationService {

    /**
     * Получить блок информацию по индексу
     */
    InformationDto getDtoById(Integer id);

    /**
     * Получить все блоки информации
     */
    List<InformationDto> getAllDto();
}
