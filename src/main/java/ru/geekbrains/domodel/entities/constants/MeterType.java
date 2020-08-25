package ru.geekbrains.domodel.entities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Перечисление вариантов различных типов счетчиков
  */
@Getter
@RequiredArgsConstructor
public enum MeterType {

    ELECTRICITY_UNIFIED("Счётчик электроэнергии (однотарифный)", "кВТ/час"),
    ELECTRICITY_DAY("Счётчик электроэнергии (дневной)", "кВТ/час"),
    ELECTRICITY_NIGHT("Счётчик электроэнергии (ночной)", "кВТ/час"),
    GAS("Счётчик газа", "куб.метров"),
    HOT_WATER("Счётчик горячей воды", "куб.метров"),
    COLD_WATER("Счётчик холодной воды", "куб.метров");

    // Описание счетчика
    private final String description;

    // Единица измерения показаний счетчика
    private final String measure;
}
