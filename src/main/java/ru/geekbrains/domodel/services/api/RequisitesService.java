package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.RequisitesDto;

/**
 * Интерфейс сервиса реквизитов компании
 */
public interface RequisitesService {

    /**
     * Получить текущие реквизиты
     */
    RequisitesDto getRelevantDto();
}
