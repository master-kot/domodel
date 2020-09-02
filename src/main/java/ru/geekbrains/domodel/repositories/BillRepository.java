package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Bill;

import java.util.List;

/**
 * Репозиторий счетов (платежных документов)
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findAllByAccountId(List<Long> id);
    List<Bill> findAllByAccount(Account account);
}
