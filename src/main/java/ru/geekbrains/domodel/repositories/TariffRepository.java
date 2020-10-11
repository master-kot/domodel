package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Tariff;

import java.util.Optional;

/**
 * Репозиторий тарифов
 */
@Repository
public interface TariffRepository  extends JpaRepository<Tariff, Integer> {

    Optional<Tariff> findByDescription(String description);
}
