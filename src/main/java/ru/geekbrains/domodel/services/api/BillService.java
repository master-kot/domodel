package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Bill;

import java.util.List;

/**
 * Интерфейс сервиса счетов (платежных документов)
 */
public interface BillService {

    /**
     * Получить список всех счетов
     *
     * @return список счетов
     */
    List<Bill> getAllBills();
}
