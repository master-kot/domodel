package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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

    @NotNull(message = DATA_NOT_BLANK + "ИНН")
    @Positive(message = DATA_NOT_POSITIVE + "ИНН")
    @Digits(integer = 10, fraction = 0, message = DATA_NOT_CONTAIN_10_DIGITS + "ИНН")
    private Long inn;

    @NotNull(message = DATA_NOT_BLANK + "КПП")
    @Positive(message = DATA_NOT_POSITIVE + "КПП")
    @Digits(integer = 9, fraction = 0, message = DATA_NOT_CONTAIN_9_DIGITS + "КПП")
    private Long kpp;

    @NotNull(message = DATA_NOT_BLANK + "ОГРН")
    @Positive(message = DATA_NOT_POSITIVE + "ОГРН")
    @Digits(integer = 15, fraction = 0, message = DATA_NOT_CONTAIN_15_DIGITS + "ОГРН")
    private Long ogrn;

    @NotBlank(message = DATA_NOT_BLANK + "Номер расчетного счета")
    private String bankAccount;

    @NotBlank(message = DATA_NOT_BLANK + "Наименование банка")
    private String bankName;

    @NotNull(message = DATA_NOT_BLANK + "БИК")
    @Positive(message = DATA_NOT_POSITIVE + "БИК")
    @Digits(integer = 9, fraction = 0, message = DATA_NOT_CONTAIN_9_DIGITS + "БИК")
    private Long bik;

    @NotBlank(message = DATA_NOT_BLANK + "Номер корреспондентского счета")
    private String correspondentAccount;
}
