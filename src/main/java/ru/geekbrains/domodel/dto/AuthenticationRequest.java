package ru.geekbrains.domodel.dto;

import lombok.Data;

/**
 * DTO класс запроса авторизации
 */
@Data
public class AuthenticationRequest {

    private String username;

    private String password;
}
