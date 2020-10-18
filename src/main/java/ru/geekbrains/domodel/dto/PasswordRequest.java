package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static ru.geekbrains.domodel.entities.constants.Messages.INVALID_PASSWORD_LENGTH;
import static ru.geekbrains.domodel.entities.constants.Messages.PASSWORD_NOT_BLANK;

/**
 * Запрос на изменение пароля
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordRequest {

    private String username;

    @Size(min=4, max=20, message = INVALID_PASSWORD_LENGTH)
    @NotBlank(message = PASSWORD_NOT_BLANK)
    private String oldPassword;

    @Size(min=4, max=20, message = INVALID_PASSWORD_LENGTH)
    @NotBlank(message = PASSWORD_NOT_BLANK)
    private String newPassword;

    private String newPasswordConfirm;
}
