package ru.geekbrains.domodel.services.core;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.mappers.AccountMapper;
import ru.geekbrains.domodel.repositories.AccountRepository;
import ru.geekbrains.domodel.services.api.AccountService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса лицевых счетов
 */
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    // Репозиторий лицевых счетов
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDto getAccountDtoById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::accountToAccountDto)
                .orElseThrow(() -> new RuntimeException("account not exist"));
    }

    @Override
    public List<AccountDto> getAllDto() {
        return accountRepository.findAll().stream()
                .map(accountMapper::accountToAccountDto).collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> getAllDtoByUserUsername(String username) {
        return accountRepository.findAllByUserUsername(username).stream()
                .map(accountMapper::accountToAccountDto).collect(Collectors.toList());
    }

    @Override
    public List<Account> getAllByUserUsername(String username) {
        return accountRepository.findAllByUserUsername(username);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found account by id: " + id));
    }
}
