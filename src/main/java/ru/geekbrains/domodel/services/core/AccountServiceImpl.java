package ru.geekbrains.domodel.services.core;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.repositories.AccountRepository;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.AccountService;

import java.util.List;

/**
 * Реализация сервиса лицевых счетов
 */
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    // Репозиторий лицевый счетов
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountByUserName(String userName) {
       User user = userRepository.findByUsername(userName).orElseThrow(
               () -> new NullPointerException("not found User: " + userName)
       );

       return accountRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("not found Account by User: " + userName)
       );
    }

    @Override
    public List<Account> getAccounts(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(
                () -> new NullPointerException("not found User: " + userName)
        );
        return accountRepository.findAllByUser(user).orElseThrow(
                () -> new NullPointerException("not found Account by User: " + userName)
        );
    }
}
