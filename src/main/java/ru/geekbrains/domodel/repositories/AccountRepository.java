package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Account;

/**
 * Репозиторий лицевых счетов
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
