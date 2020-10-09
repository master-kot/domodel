package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Запрос для регистрации нового Лицевого счета
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRequest {

    private String address;

    private String houseNumber;

    private Double acresAmount;
}
