package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.InformationDto;
import ru.geekbrains.domodel.dto.InformationRequest;

import java.util.List;

/**
 * Интерфейс сервиса блоков информации о компании
 */
public interface InformationService {

    /**
     * Получает блок информации по индексу
     */
    InformationDto getDtoById(Integer id);

    /**
     * Получает все блоки информации
     */
    List<InformationDto> getAllDto();

    /**
     * Сохраняет блок информации
     */
    InformationDto save(InformationRequest appealRequest);

    /**
     * Изменяет блок информации
     */
    InformationDto update(InformationDto informationRequest);
}
