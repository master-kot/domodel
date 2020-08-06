package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Meter;

/**
 * Репозиторий счетчиков
 */
@Repository
public interface MeterRepository extends JpaRepository<Meter, Integer> {

}
