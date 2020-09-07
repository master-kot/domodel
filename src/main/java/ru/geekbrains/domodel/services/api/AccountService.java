package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.entities.Account;

import java.util.List;

/**
 * Интерфейс сервиса лицевых счетов
 */
public interface AccountService {

    /**
     * Получить лицевой счет по ID
     */
    AccountDto getById(Long id);

    /**
     * Получить список всех лицевых счетов
     */
    List<AccountDto> getAll();

    /**
     * Получить список лицевых счетов пользователя по его логину
     */
    List<AccountDto> getAllByUserUsername(String username);

    /**
     * Получить список лицевых счетов пользователя по его логину
     * Используется только для межсервисного взаимодействия!!!
     */
    List<Account> getAllAccountsByUserUsername(String username);
}
