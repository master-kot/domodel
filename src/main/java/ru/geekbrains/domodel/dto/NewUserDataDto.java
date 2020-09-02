package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO представление сущности запроса на регистрацию нового Пользователя
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewUserDataDto {

    private String username;

    private String password;

    private String passwordConfirm;

    private String email;
}
