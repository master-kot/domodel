package ru.geekbrains.domodel.entities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Перечисление вариантов различных типов счетчиков
  */
@Getter
@RequiredArgsConstructor
public enum MeterType {

    ELECTRICITY_UNIFIED("Однотарифный счётчик электроэнергии", "кВТ/час"),
    ELECTRICITY_DAY("Дневной счётчик электроэнергии", "кВТ/час"),
    ELECTRICITY_NIGHT("Ночной счётчик электроэнергии", "кВТ/час"),
    GAS("Счётчик газа", "куб.метров"),
    HOT_WATER("Счётчик горячей воды", "куб.метров"),
    COLD_WATER("Счётчик холодной воды", "куб.метров");

    // Описание счетчика
    private final String description;

    // Единица измерения показаний счетчика
    private final String measure;
}
