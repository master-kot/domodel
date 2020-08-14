package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий лицевых счетов
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    // TODO Кто решает, сколько аккаунтов у пользователя? Возможно лучше, чтобы все таки был один?
    // TODO Возможно лучше было бы Реализовать поиск по username?
    /**
     * Найти аккаунт по пользователю
     */
    Optional<Account> findByUser(User user);
    
    /**
     * Найти список аккаунтов пользователя
     */
    Optional<List<Account>> findAllByUser(User user);
}
