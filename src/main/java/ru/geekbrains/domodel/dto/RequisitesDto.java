package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Dto представление сущности Реквизиты
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequisitesDto implements Dto {

    private Integer id;

    // Название юридического лица
    private String companyName;

    // Адрес юридического лица
    private String companyAddress;

    // ИНН организации
    private Long inn;

    // КПП организации
    private Long kpp;

    // ОГРН организации
    private Long ogrn;

    // Номер расчетного счета
    private String bankAccount;

    // Наименование банка
    private String bankName;

    // БИК организации
    private Long bik;

    // Номер корреспондентского счета
    private String correspondentAccount;
}
