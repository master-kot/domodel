package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Meter;

import java.util.Optional;

/**
 * Репозиторий счетчиков показаний
 */
@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {

    Optional<Meter> findByMeterNumber(Integer meterNum);
    Optional<Meter> findByAccount(Account account);
}
