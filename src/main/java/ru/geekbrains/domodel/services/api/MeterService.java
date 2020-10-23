package ru.geekbrains.domodel.services.api;

import org.springframework.security.core.Authentication;
import ru.geekbrains.domodel.dto.*;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс сервиса счетчиков показаний
 */
public interface MeterService {

    /*
     * СОГЛАШЕНИЕ О НАИМЕНОВАНИИ МЕТОДОВ СЕРВИСОВ
     * NewsDto getNewsById(Long id) найти объект по параметру
     * List<NewsDto> getAllNews() найти все объекты
     * List<NewsDto> getAllNewsByUser(UserDto user) найти все объекты по параметру
     * News updateNews(NewsDto news) изменить объект
     * News saveNews(NewsDto newsDto) сохранить объект
     * List<NewsDto> saveAllNews(List<NewsDto> newsDtoList) сохранить список объектов
     * void deleteNews(NewsDto newsDto) удалить конкретный объект
     * Long deleteNewsById(Long id) удалить объект по параметру
     * void deleteAllNews(List<NewsDto> newsDtoList) удалить список объектов
     */
    /**
     * Получить счетчик по id
     */
    MeterDto getMeterById(Long id, Authentication authentication);

    /**
     * Получить список счетчиков данного аккаунта
     */
    List<Meter> getAllMetersByAccount(Account account);
    /**
     * Получить список счетчиков данного пользователя
     */
    List<MeterDto> getAllMetersByUserName(String name);
    /**
     * Получить список всех счетчиков
     */
    List<MeterDto> getAllMeters(Authentication authentication);
    /**
     * Получить список всех счетчиков для пользователя
     */
    List<AccountMetersDto> getMetersByUserUsername(Authentication authentication);

    /**
     * Получить счетчик по его серийному номеру
     */
    Meter getMeterBySerialNumber(Integer serialNumber);

    /**
     * Сохранить данные счетчика.
     */
    MeterDto saveOrUpdate(Long id, MeterDto meterDto);

    /**
     * Удаление данных счетчика.
     */
    Integer deleteMeterById(Long id);

    /**
     * Принять единичные данные о показаниях счетчика.
     * Предусмотреть, что показания могут быть поданы только раз в месяц,
     * при попытке повторного сохранения показания в текущем месяце - изменять его
     */
    MeterDataDto submitMeterData(Long meterId, Double meterDataDto, Authentication authentication);

    /**
     * Принять данные о показаниях счетчиков.
     * Предусмотреть, что показания могут быть поданы только раз в месяц,
     * при попытке повторного сохранения показания в текущем месяце - изменять его
     */
    List<MeterDataDto> submitAllMeterData(List<SubmitDataDto> submitData, Authentication authentication);

    /**
     * Получить список всех показаний данного счетчика
     */
    List<MeterDataDto> getAllMeterDataByMeterId(Long id, Authentication authentication);

    /**
     * Получить предыдущее (предпоследнее по дате в списке) показание счетчика
     */
    Optional<MeterData> getPreviousMeterDataByMeter(Meter meter);

    /**
     * Получить предыдущие (предпоследние в списке по датам) показания для списка счетчиков
     */
    List<MeterData> getPreviousMeterDataByMeters(List<Meter> meter);

    /**
     * Получить текущее (последнее по дате в списке) показание счетчика
     */
    MeterData getCurrentMeterDataByMeter(Meter meter);

    /**
     * Получить текущие (последние по датам в списке) показания для списка счетчиков
     */
    List<MeterData> getCurrentMeterDataByMeters(List<Meter> meter);

    List<MeterData> getAllDataByMeters(List<Meter> meters);

    Integer deleteMeterDataById(Long dataId);

    /**
     * Получить список всех типов счетчика
     */
    List<MeterTypeDto> getMeterTypes();

    /**
     * Получить список всех тарифов
     */
    List<TariffDto> getMeterTariffs();
}
