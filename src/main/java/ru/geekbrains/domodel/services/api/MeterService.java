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
    List<Meter> getAllMetersByAccount(Account account);

    /**
     * Получить список всех счетчиков
     */
    List<Meter> getAllMeters();

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
    List<MeterData> getAllMeterDataByMeter(Meter meter);

    /**
     * Получить предыдущие (предпоследние в списке по дате) показания счетчика
     */
    Optional<MeterData> getPreviousMeterDataByMeter(Meter meter);

    /**
     * Получить предыдущие (предпоследние в списке по дате) показания списка счетчиков
     */
    List<MeterData> getPreviousMeterDatasByMeters(List<Meter> meter);

    /**
     * Получить текущие (последние в списке по дате) показания счетчика
     */
    Optional<MeterData> getCurrentMeterDataByMeter(Meter meter);

    /**
     * Получить текущие (последние в списке по дате) показания списка счетчиков
     */
    List<MeterData> getCurrentMeterDatasByMeters(List<Meter> meter);
}
