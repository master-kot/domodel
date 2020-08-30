package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Перечисление вариантов различных типов статуса обращения
 */
@Getter
@RequiredArgsConstructor
public enum AppealStatus {

    SENT("Направлено"),
    RECEIVED("Получено"),
    IN_PROGRESS("В работе"),
    DONE("Выполнено"),
    CANCELLED("Отменено");

    // Описание статуса обращения
    private final String description;
}
