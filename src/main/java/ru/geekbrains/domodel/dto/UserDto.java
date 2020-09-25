package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO представление сущности Пользователь
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Dto {

    private Long id;

    private String username;

    private LocalDate creationDate;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String email;

    private String photoLink;

    private String address;

    private String phoneNumber;
}
