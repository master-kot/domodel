package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий данных показаний счетчика
 */
public interface MeterDataRepository extends JpaRepository<MeterData, Long> {
    
    /**
     * Получить список показаний счетчика
     */
    List<MeterData> findAllByMeter(Meter meter);

    List<MeterData> findAllByMeterOrderByCreationDateDesc(Meter meter);

    @Query(value = "select data from MeterData data where data.meter = :meter and data.creationDate = " +
            "(select max(maxData.creationDate) from MeterData maxData where maxData.meter = data.meter)")
    Optional<MeterData> findCurrentMeterData(Meter meter);

    @Query(value = "SELECT md from MeterData md WHERE md.meter IN :meters AND md.creationDate = " +
            "(SELECT MAX(md2.creationDate) FROM MeterData md2 WHERE md2.meter = md.meter)")
    Optional<List<MeterData>> findCurrentMeterData(@NotEmpty List<Meter> meters);

    Optional<MeterData> findFirstByMeterAndCreationDateBefore(Meter meter, LocalDate creationDate);

    Integer deleteMeterDataById(Long id);
}
