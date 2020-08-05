package ru.geekbrains.domodel.users.entities;

import javax.validation.constraints.NotBlank;

/**
 * Представление сущности пользователя для работы с сервисами
 */
public class UserRepresentation {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirm;

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

    public UserRepresentation() {
    }

    public UserRepresentation(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
