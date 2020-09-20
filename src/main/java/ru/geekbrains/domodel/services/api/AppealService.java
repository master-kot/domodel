package ru.geekbrains.domodel.services.api;

import org.springframework.security.core.Authentication;
import ru.geekbrains.domodel.dto.AppealDto;
import ru.geekbrains.domodel.dto.AppealRequest;

import java.util.List;

/**
 * Интерфейс сервиса обращений
 */
public interface AppealService {

    /**
     * Получить обращение по индексу
     */
    AppealDto getDtoById(Long id, Authentication authentication);

    /**
     * Изменить обращение
     */
    AppealDto update(AppealDto appealDto, Authentication authentication);

    /**
     * Сохранить обращение
     */
    AppealDto save(AppealRequest appealRequest, Authentication authentication);

    /**
     * Получить список всех обращений
     */
    List<AppealDto> getAllDtoByUser(Authentication authentication);
}
