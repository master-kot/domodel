package ru.geekbrains.domodel.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.repositories.AccountRepository;
import ru.geekbrains.domodel.services.api.AccountService;

import java.util.List;

/**
 * Реализация сервиса лицевых счетов
 */
@Service
public class AccountServiceImpl implements AccountService {

    // Репозиторий лицевый счетов
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
