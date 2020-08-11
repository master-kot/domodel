package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Представление сущности пользователя для работы с фронтэндом
 */
@Getter
@Setter
@NoArgsConstructor
public class UserRepresentation {

    @NotBlank
    @Size(min = 5, message="Номер телефона не менее 5 символов")
    private String username;

    @NotBlank
    @Size(min = 5, message="Пароль должен быть не менее 5 символов")
    private String password;

    @NotBlank
    @Size(min = 5, message="Пароль должен быть не менее 5 символов")
    private String passwordConfirm;

    private String firstName;

    private String secondName;

    private String middleName;

    @Email
    private String email;
}
