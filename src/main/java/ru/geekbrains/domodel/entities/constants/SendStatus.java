package ru.geekbrains.domodel.entities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Статус отправки счета пользователю
  */
@Getter
@RequiredArgsConstructor
public enum SendStatus {

    NEW("Созданный новый счет"),
    SENT("Отправлен пользователю"),
    RECEIVED("Просмотрен пользователем"),
    CANCELED("Отменен администрацией");

    // Описание статуса
    private final String description;
}
