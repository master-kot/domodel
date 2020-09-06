package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO сущность роли пользователя
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {

    private String role;
}
