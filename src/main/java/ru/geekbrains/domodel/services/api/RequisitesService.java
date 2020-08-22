package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Requisites;
import ru.geekbrains.domodel.entities.constants.BillType;

import java.util.List;

/**
 * Интерфейс сервиса реквизитов компании
 */
public interface RequisitesService {

    /**
     * Получить список реквизитов всех компаний
     */
    List<Requisites> getAllRequisites();

    /**
     * Получить список реквизитов компании
     */
    Requisites getRequisitesByBillType(BillType billType);
}
