package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.dto.AccountRequest;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.entities.Account;

import java.util.List;

/**
 * Интерфейс сервиса лицевых счетов
 */
public interface AccountService {

    /**
     * Получить лицевой счет по ID
     */
    AccountDto getAccountDtoById(Long id);

    /**
     * Получить список всех лицевых счетов
     */
    List<AccountDto> getAllDto();

    /**
     * Получить список лицевых счетов пользователя по его логину
     */
    List<AccountDto> getAllDtoByUserUsername(String username);

    /**
     * Получить список лицевых счетов пользователя по его логину
     * Используется только для межсервисного взаимодействия!!!
     */
    List<Account> getAllAccountsByUserUsername(String username);

    /**
     * Получить лицевой счет по ID
     */
    Account getAccountById(Long id);

    /**
     * Создать новый лицевой счет
     *
     * @param accountRequest отображение данных лицевого счета
     * @return лицевой счет, сохраненный в репозитории
     */
    AccountDto save(AccountRequest accountRequest);

    /**
     * Изменить данные лицевого счета
     *
     * @param accountDto лицевой счет с измененными данными
     */
    AccountDto update(AccountDto accountDto);
}
