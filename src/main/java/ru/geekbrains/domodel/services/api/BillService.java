package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Bill;
import ru.geekbrains.domodel.entities.constants.SendStatus;

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

    Bill save(Bill bill);

    Bill findById(Long billId);

    List<Bill> findAllByUsername(String username);

    // создать счет для аккаунта
    Bill createBillByAccount(Account account);

    //  создать счета для всех аккаунтов
    List<Bill> createAll();

    List<Bill> getAllByAccount(Account account);

    List<Bill> getAllByStatus(SendStatus sendStatus);
    List<Bill> getAllByStatusAndAccount(SendStatus status, Account account);
    Bill changeById();
}
