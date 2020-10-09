package ru.geekbrains.domodel.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.domodel.entities.MeterType;

import java.util.Optional;

public interface MeterTypeRepository extends CrudRepository<MeterType, Integer> {
    Optional<MeterType> findByDescription(String desc);
}
