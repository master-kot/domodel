package ru.geekbrains.domodel.services.api;

import org.springframework.security.core.Authentication;
import ru.geekbrains.domodel.dto.AppealDto;

import java.util.List;

/**
 * Интерфейс сервиса обращений
 */
public interface AppealService {

    /**
     * Получить обращение по индексу
     */
    AppealDto getById(Long id, Authentication authentication);

    /**
     * Изменить обращение
     */
    AppealDto update(AppealDto appealDto, Authentication authentication);

    /**
     * Получить список всех обращений
     */
    AppealDto save(AppealDto appealDto, Authentication authentication);

    /**
     * Получить список всех обращений
     */
    List<AppealDto> getAll(Authentication authentication);
}
