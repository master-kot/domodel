package ru.geekbrains.domodel.entities.common;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущность Общего пользователя для межсервисного взаимодействия
 */
@Getter
@Setter
@NoArgsConstructor
public class UserCommon {

    // Логин
    private String username;

    // Список ролей
    private List<String> roles = new ArrayList<>();

    // Список номеров лицевых счетов пользователя
    private List<Long> accountIds = new ArrayList<>();
}
