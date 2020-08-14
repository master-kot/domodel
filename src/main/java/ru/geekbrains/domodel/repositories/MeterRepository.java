package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Meter;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий счетчиков показаний
 */
@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {

    /**
     * Получить список счетчиков аккаунта
     */
    Optional<List<Meter>> findByAccount(Account account);

    /**
     * Найти счетчик по его серийному номеру
     */
    Optional<Meter> findBySerialNumber(Integer serialNumber);

    /**
     *
     */
    Optional<Meter> findById(Long id);
}
