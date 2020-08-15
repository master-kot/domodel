package ru.geekbrains.domodel.entities.constants;

import lombok.Getter;

/**
 * Статус отправки счета пользователю
  */
public enum SendStatus {

    NEW("Созданный новый счет"),
    SENT("Отправлен пользователю"),
    RECEIVED("Просмотрен пользователем"),
    CANCELED("Отменен администрацией");

    @Getter
    // Описание статуса
    private final String description;

    SendStatus(String description) {
        this.description = description;
    }
}
