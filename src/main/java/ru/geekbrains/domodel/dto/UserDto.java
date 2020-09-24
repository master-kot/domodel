package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * DTO представление сущности Пользователь
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String email;

    private String photoLink;

    private String address;

    private String phoneNumber;

    private List<AccountDto> accounts;
}
