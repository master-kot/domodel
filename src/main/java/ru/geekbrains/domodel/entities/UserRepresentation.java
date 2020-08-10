package ru.geekbrains.domodel.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Представление сущности пользователя для работы с фронтэндом
 */
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

    @Email
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRepresentation() {
    }
}
