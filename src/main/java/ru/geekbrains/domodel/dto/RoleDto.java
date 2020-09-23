package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO представление сущности Роль пользователя
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {

    private String role;
}
