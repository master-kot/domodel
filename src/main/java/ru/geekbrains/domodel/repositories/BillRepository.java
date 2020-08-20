package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Bill;
import ru.geekbrains.domodel.entities.constants.SendStatus;

import java.util.List;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий счетов (платежных документов)
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByAccount(Account user);
    List<Bill> findAllBySendStatus(SendStatus status);
    List<Bill> findAllBySendStatusAAndAccount(SendStatus status, Account account);

    //List<Bill> findAllByAccount(Account account);
}
