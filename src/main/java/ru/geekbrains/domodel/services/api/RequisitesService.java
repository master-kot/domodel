package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Requisites;

import java.util.List;

/**
 * Интерфейс сервиса реквизитов компании
 */
public interface RequisitesService {
    /**
     * Получить список всех реквизитов компании
     *
     * @return список реквизитов компании
     */
    List<Requisites> getAllRequisites();
}
