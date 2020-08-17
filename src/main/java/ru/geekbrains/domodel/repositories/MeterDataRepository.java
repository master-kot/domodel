package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;

import java.util.List;

/**
 * Репозиторий данных показаний счетчика
 */
public interface MeterDataRepository extends JpaRepository<MeterData, Long> {
    
    /**
     * Получить список показаний счетчика
     */
    List<MeterData> findAllByMeter(Meter meter);

    List<MeterData> findAllByMeterOrderByCreationDateDesc(Meter meter);
}
