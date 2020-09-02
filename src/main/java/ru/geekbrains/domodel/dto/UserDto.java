package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO представление сущности Пользователь
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private String phone;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String email;

    private String photoLink;

    private String address;
}
