package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.TariffDto;

import java.util.List;

/**
 * Сервис тарифов
 */
public interface TariffService {

    /**
     * Получить список всех тарифов
     */
    List<TariffDto> getAllTariffs();

    /**
     * Получить тариф по его Id
     */
    TariffDto getTariffById(Integer id);
}
