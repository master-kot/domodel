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

    /**
     * Получить список счетчиков данного аккаунта
     */
    Optional<List<Meter>> getAllMetersByAccount(Account account);

    /**
     * Получить список всех счетчиков
     */
    Optional<List<Meter>> getAllMeters();

    /**
     * Получить счетчик по его серийному номеру
     */
    Meter getMeterBySerialNumber(Integer serialNumber);

    /**
     * Сохранить данные счетчика
     */
    void save(Meter meter);

    /**
     * Принять единичные данные о показаниях счетчика
     */
    void submitMeterData(MeterData meterData);

    /**
     * Получить список всех показаний данного счетчика
     */
    Optional<List<MeterData>> getAllMeterDataByMeter(Meter meter);

    /**
     * Получить последние по дате показания данного счетчика
     */
    Optional<MeterData> getLastMeterDataByMeter(Meter meter);
}
