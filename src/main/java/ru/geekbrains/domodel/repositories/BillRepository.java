package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Bill;

/**
 * Репозиторий счетов (платежных документов)
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

}
