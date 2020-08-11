package ru.geekbrains.domodel.entities.enums;

import lombok.Getter;

public enum  MeterType {
    ELECTRICITY_UNIFIED("Счётчик электроэнергии"),
    ELECTRICITY_DAY("Дневной счётчик электроэнергии"),
    ELECTRICITY_NIGHT("Ночной счётчик электроэнергии");

    @Getter
    private String name;

    MeterType(String name) {
        this.name = name;
    }
}
