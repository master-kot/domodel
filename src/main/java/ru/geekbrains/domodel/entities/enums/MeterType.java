package ru.geekbrains.domodel.entities.enums;

import lombok.Getter;

// Перечисление вариантов различных типов счетчиков
public enum MeterType {

    ELECTRICITY_UNIFIED("Однотарифный счётчик электроэнергии"),
    ELECTRICITY_DAY("Дневной счётчик электроэнергии"),
    ELECTRICITY_NIGHT("Ночной счётчик электроэнергии"),
    GAS("Счётчик газа"),
    HOT_WATER("Счётчик горячей воды"),
    COLD_WATER("Счётчик холодной воды");

    @Getter
    // Описание счетчика
    private final String description;

    MeterType(String description) {
        this.description = description;
    }
}
