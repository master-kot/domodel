package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.dto.BillDto;
import ru.geekbrains.domodel.entities.Bill;

import java.util.List;

/**
 * Интерфейс сервиса счетов (платежей)
 */
public interface BillService {

    /**
     * Получить список всех счетов
     */
    List<BillDto> getAllDto();

    /**
     * Найти счет по его номеру
     */
    BillDto getDtoById(Long id);

    /**
     * Найти все счета для аккаунта
     */
    List<BillDto> getAllDtoByAccount(AccountDto account);

    /**
     * Найти все неоплаченные счета для аккаунта
     */
    List<BillDto> getAllUnpaidDtoByAccounts(List<AccountDto> accounts);

    /**
     * Найти все счета для аккаунта
     */
    List<BillDto> getAllDtoByAccounts(List<AccountDto> accountDtos);

    /**
     * Создать счет для аккаунта
     */
    BillDto save(BillDto bill);

    /**
     * Создать счета для всех аккаунтов
     */
    List<BillDto> saveAll();

    /**
     * Изменить счет по номеру
     */
    Bill update(BillDto bill);
}
