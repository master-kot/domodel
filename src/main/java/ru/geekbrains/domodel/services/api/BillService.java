package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Bill;

import java.util.List;
import java.util.Optional;

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

    /**
     * Получить список всех счетов
     *
     * @return список счетов
     */
    List<Bill> getAllBillsByAccount(Account account);
}
