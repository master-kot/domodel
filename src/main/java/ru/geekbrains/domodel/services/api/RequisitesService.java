package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.RequisitesDto;

/**
 * Интерфейс сервиса реквизитов компании
 */
public interface RequisitesService {

    /**
     * Получает текущие реквизиты
     */
    RequisitesDto getCurrentDto();

    /**
     * Создает текущие реквизиты компании, если не были созданы, либо изменяет текущие
     */
    RequisitesDto update(RequisitesDto requisitesDto);
}
