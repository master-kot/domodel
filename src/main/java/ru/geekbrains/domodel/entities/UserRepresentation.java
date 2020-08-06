package ru.geekbrains.domodel.entities;

import javax.validation.constraints.NotBlank;

/**
 * Представление сущности пользователя для работы с сервисами
 */
public class UserRepresentation {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirm;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public UserRepresentation(String login, String password, String passwordConfirm) {
        this.login = login;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}
