package ru.geekbrains.domodel.services.core;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.repositories.AccountRepository;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса лицевых счетов
 */
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    // Репозиторий лицевых счетов
    private final AccountRepository accountRepository;

    // Репозиторий пользователей
    private final UserRepository userRepository;

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getAccountsByUserUserame(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            return new ArrayList<>();
        }
        User user = optionalUser.get();

        Optional<List<Account>> optionalAccounts = accountRepository.findAllByUser(user);
        return optionalAccounts.orElseGet(ArrayList::new);
    }

    @Override
    public List<Account> getAccountsByUser(User user) {
        return accountRepository.findAllByUser(user).orElseGet(ArrayList::new);
    }
}
