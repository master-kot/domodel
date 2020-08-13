package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс сервиса счетчиков показаний
 */
public interface MeterService {

    Optional<Meter> getMeterByAccount(Account account);
    Optional<List<Meter>> getAllMeters();
    Meter findMeterByNum(Integer meterNum);
    void save(Meter meter);

    void submitData(MeterData meterData);
    Optional<List<MeterData>> getAllMeterData(Meter meter);
}
