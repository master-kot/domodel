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

    Meter getMeter(Long id);

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
     * Сохранить данные счетчика.
     */
    void save(Meter meter);

    /**
     * Принять единичные данные о показаниях счетчика.
     * Предусмотреть, что показания могут быть поданы только раз в месяц,
     * при попытке повторного сохранения показания в текущем месяце - изменять его
     */
    void submitMeterData(MeterData meterData);

    /**
     * Получить список всех показаний данного счетчика
     */
    List<MeterData> getAllMeterDataByMeter(Meter meter);

    /**
     * Получить предыдущее (предпоследнее по дате в списке) показание счетчика
     */
    Optional<MeterData> getPreviousMeterDataByMeter(Meter meter);

    /**
     * Получить предыдущие (предпоследние в списке по датам) показания для списка счетчиков
     */
    List<MeterData> getPreviousMeterDatasByMeters(List<Meter> meter);

    /**
     * Получить текущее (последнее по дате в списке) показание счетчика
     */
    Optional<MeterData> getCurrentMeterDataByMeter(Meter meter);

    /**
     * Получить текущие (последние по датам в списке) показания для списка счетчиков
     */
    List<MeterData> getCurrentMeterDatasByMeters(List<Meter> meter);

    /**
     * Сгенерировать показания для всех счетчиков, по которым не подавались показания в текущем месяце
     * на основании заданного в сущности Тариф значения по умолчанию для этого типа счетчика.
     * Предусмотреть защиту от повторного запуска метода в одном расчетном месяце (периоде)
     */
    @Deprecated
    void generateDefaultMeterData();

    List<MeterData> getAllDataByMeters(List<Meter> meters);
}
