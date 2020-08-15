package ru.geekbrains.domodel.entities.constants;

import lombok.Getter;

/**
 * Перечисление вариантов различных типов счетчиков
  */
public enum MeterType {

    ELECTRICITY_UNIFIED("Однотарифный счётчик электроэнергии", "кВТ/час"),
    ELECTRICITY_DAY("Дневной счётчик электроэнергии", "кВТ/час"),
    ELECTRICITY_NIGHT("Ночной счётчик электроэнергии", "кВТ/час"),
    GAS("Счётчик газа", "куб.метров"),
    HOT_WATER("Счётчик горячей воды", "куб.метров"),
    COLD_WATER("Счётчик холодной воды", "куб.метров");

    @Getter
    // Описание счетчика
    private final String description;

    @Getter
    // Единица измерения показаний счетчика
    private final String measure;

    MeterType(String description, String measure) {
        this.description = description;
        this.measure = measure;
    }
}
