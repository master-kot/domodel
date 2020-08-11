package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;

import java.util.List;
import java.util.Optional;

public interface MeterDataRepository extends JpaRepository<MeterData, Long> {
    Optional<List<MeterData>> findByMeter(Meter meter);
}
