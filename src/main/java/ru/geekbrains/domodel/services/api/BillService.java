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
    List<BillDto> getAllDto();

    /**
     * Найти счет по его номеру
     */
    BillDto getDtoById(Long id);

    /**
     * Найти все счета по имени пользователя
     */
    List<BillDto> getAllDtoByUserUsername(String username);

    /**
     * Найти все счета для аккаунта
     */
    List<BillDto> getAllDtoByAccount(AccountDto account);

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
