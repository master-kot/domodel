package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static ru.geekbrains.domodel.entities.constants.Messages.INVALID_USERNAME_LENGTH;
import static ru.geekbrains.domodel.entities.constants.Messages.USERNAME_NOT_BLANK;

/**
 * Dto представление сущности Пользователь
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;

    @Size(min=4, max=50, message = INVALID_USERNAME_LENGTH)
    @NotBlank(message = USERNAME_NOT_BLANK)
    private String username;

    private String creationDate;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String email;

    private String photoLink;

    private String address;

    private String phoneNumber;
}
