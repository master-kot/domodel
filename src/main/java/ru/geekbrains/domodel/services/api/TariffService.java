package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Tariff;

import java.util.List;
import java.util.Optional;

/**
 * Сервис тарифов
 */
public interface TariffService {

    /**
     * Получить список всех тарифов
     */
    List<Tariff> getAllTariffs();

    /**
     * Получить тариф по его Id
     */
    Optional<Tariff> getById(Integer id);
}
