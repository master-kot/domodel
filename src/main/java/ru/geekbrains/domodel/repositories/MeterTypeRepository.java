package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.domodel.entities.MeterType;

import java.util.Optional;

public interface MeterTypeRepository extends JpaRepository<MeterType, Integer> {
    Optional<MeterType> findByDescription(String description);
}
