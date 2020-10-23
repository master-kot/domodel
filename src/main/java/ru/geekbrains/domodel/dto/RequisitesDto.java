package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import static ru.geekbrains.domodel.entities.constants.Messages.*;

/**
 * Dto представление сущности Реквизиты
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequisitesDto {

    @NotBlank(message = DATA_NOT_BLANK + "Название юридического лица")
    private String companyName;

    @NotBlank(message = DATA_NOT_BLANK + "Адрес юридического лица")
    private String companyAddress;

    @NotBlank(message = DATA_NOT_BLANK + "ИНН")
    @Digits(integer = 10, fraction = 0, message = DATA_ONLY_DIGITS + "ИНН")
    private Long inn;

    @NotBlank(message = DATA_NOT_BLANK + "КПП")
    @Digits(integer = 9, fraction = 0, message = DATA_ONLY_DIGITS + "КПП")
    private Long kpp;

    @NotBlank(message = DATA_NOT_BLANK + "ОГРН")
    @Digits(integer = 15, fraction = 0, message = DATA_ONLY_DIGITS + "ОГРН")
    private Long ogrn;

    @NotBlank(message = DATA_NOT_BLANK + "Номер расчетного счета")
    private String bankAccount;

    @NotBlank(message = DATA_NOT_BLANK + "Наименование банка")
    private String bankName;

    @NotBlank(message = DATA_NOT_BLANK + "БИК")
    @Digits(integer = 9, fraction = 0, message = DATA_ONLY_DIGITS + "БИК")
    private Long bik;

    @NotBlank(message = DATA_NOT_BLANK + "Номер корреспондентского счета")
    private String correspondentAccount;
}
