package ru.geekbrains.domodel.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import static ru.geekbrains.domodel.entities.constants.Messages.PASSWORD_NOT_BLANK;
import static ru.geekbrains.domodel.entities.constants.Messages.USERNAME_NOT_BLANK;

/**
 * Запрос авторизации
 */
@Data
public class AuthenticationRequest {

    @NotBlank(message = USERNAME_NOT_BLANK)
    private String username;

    @NotBlank(message = PASSWORD_NOT_BLANK)
    private String password;
}
