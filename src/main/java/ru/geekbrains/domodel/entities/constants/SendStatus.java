package ru.geekbrains.domodel.entities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Статус отправки счета пользователю
  */
@RequiredArgsConstructor
public enum SendStatus {

    NEW("Созданный новый счет"),
    SENT("Отправлен пользователю"),
    RECEIVED("Просмотрен пользователем"),
    CANCELED("Отменен администрацией");

    @Getter
    // Описание статуса
    private final String description;
}
