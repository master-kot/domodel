package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.AccountDto;

import java.util.List;

/**
 * Интерфейс сервиса лицевых счетов
 */
public interface AccountService {

    /**
     * Получить лицевой счет по ID
     */
    AccountDto getAccountById(Long id);

    /**
     * Получить список всех лицевых счетов
     */
    List<AccountDto> getAllAccounts();

    /**
     * Получить список лицевых счетов пользователя по его логину
     */
    List<AccountDto> getAccountsByUserUsername(String username);
}
