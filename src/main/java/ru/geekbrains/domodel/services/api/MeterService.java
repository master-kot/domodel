package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
import ru.geekbrains.domodel.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс сервиса счетчиков показаний
 */
public interface MeterService {

    Optional<Meter> getMeter(String userName);
    Optional<List<Meter>> getAllMeters();
    Optional<List<Meter>> getMetersByUser(String userName);
    Meter findMeterByNum(Integer meterNum);
    void save(Meter meter, String userName);

    Optional<List<MeterData>> getAllMeterData(Meter meter);
}
