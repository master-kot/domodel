package ru.geekbrains.domodel.entities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Перечисление вариантов различных типов счетов
 */
@RequiredArgsConstructor
public enum BillType {

    TEST("Тестовое описание");

    @Getter
    // Описание статуса
    private final String description;

    //TODO продумать какие могут быть здесь

}
