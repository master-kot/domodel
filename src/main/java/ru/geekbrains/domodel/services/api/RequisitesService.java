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
     * Получить реквизиты компании по типу платежа
     */
    Requisites getRequisitesByBillType(BillType billType);
}
