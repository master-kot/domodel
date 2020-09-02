package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.dto.BillDto;
import ru.geekbrains.domodel.entities.Bill;

import java.util.List;

/**
 * Интерфейс сервиса счетов (платежных документов)
 */
public interface BillService {

    /**
     * Получить список всех счетов
     */
    List<BillDto> getAllBills();

    /**
     * Найти счет по его номеру
     */
    BillDto getBillById(Long id);

    /**
     * Найти все счета по имени пользователя
     */
    List<BillDto> getAllBillsByUsername(String username);

    /**
     * Найти все счета для аккаунта
     */
    List<BillDto> getAllBillsByAccount(AccountDto account);

    /**
     * Создать счет для аккаунта
     */
    BillDto saveBill(BillDto bill);

    /**
     * Создать счета для всех аккаунтов
     */
    List<BillDto> saveAllBills();

    /**
     * Изменить счет по номеру
     */
    Bill updateBill(BillDto bill);
}
