package ru.geekbrains.domodel.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.domodel.entities.MeterType;

public interface MeterTypeRepository extends CrudRepository<MeterType, Integer> {
    MeterType findByDescription(String desc);
}
