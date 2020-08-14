package ru.geekbrains.domodel.entities.enums;

import lombok.Getter;

// Варианты типов счетчиков
public enum  MeterType {
    ELECTRICITY_UNIFIED("Однотарифный счётчик электроэнергии"),
    ELECTRICITY_DAY("Дневной счётчик электроэнергии"),
    ELECTRICITY_NIGHT("Ночной счётчик электроэнергии"),
    GAS("Счётчик газа"),
    HOT_WATER("Счётчик горячей воды"),
    COLD_WATER("Счётчик холодной воды");

    @Getter
    private String name;

    MeterType(String name) {
        this.name = name;
    }
}
