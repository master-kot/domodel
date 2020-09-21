package ru.geekbrains.domodel.services.api;

import org.springframework.security.core.Authentication;
import ru.geekbrains.domodel.dto.InformationDto;
import ru.geekbrains.domodel.dto.InformationRequest;

import java.util.List;

/**
 * Интерфейс сервиса информации о компании
 */
public interface InformationService {

    /**
     * Получить блок информации по индексу
     */
    InformationDto getDtoById(Integer id);

    /**
     * Получить все блоки информации
     */
    List<InformationDto> getAllDto();

    /**
     * Сохранить блок информации
     */
    InformationDto save(InformationRequest appealRequest, Authentication authentication);
}
