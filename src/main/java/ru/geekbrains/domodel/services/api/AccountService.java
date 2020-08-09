package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Account;

import java.util.List;

/**
 * Интерфейс сервиса лицевых счетов
 */
public interface AccountService {

    /**
     * Получить список всех лицевых счетов
     *
     * @return список лицевых счетов
     */
    List<Account> getAllAccounts();
}
