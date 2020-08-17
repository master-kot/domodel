package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.User;

import java.util.List;

/**
 * Интерфейс сервиса лицевых счетов
 */
public interface AccountService {

    /**
     * Получить лицевой счет по ID
     */
    Account getAccountById(Long id);

    /**
     * Получить список всех лицевых счетов
     */
    List<Account> getAllAccounts();

    /**
     * Получить список лицевых счетов пользователя по его логину
     */
    List<Account> getAccountsByUserUserame(String username);

    /**
     * Получить список лицевых счетов пользователя
     */
    List<Account> getAccountsByUser(User user);
}
