package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Account;

import java.util.List;

/**
 * Репозиторий лицевых счетов
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Найти список аккаунтов пользователя
     */
    List<Account> findAllByUserUsername(String username);
}
