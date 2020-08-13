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
    Optional<Account> findByUser(User user);
    Optional<List<Account>> findAllByUser(User user);
}
