package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Meter;

import java.util.List;

/**
 * Интерфейс сервиса счетчиков показаний
 */
public interface MeterService {

    List<Meter> getAllMeters();
}
