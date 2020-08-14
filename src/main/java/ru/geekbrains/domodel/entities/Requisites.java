package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность платежных реквизитов компании.
 * Используются для отображения информации о компании и формирования платежек
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "requisites")
public class Requisites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Название юридического лица
    @Column(name = "company_name")
    private Integer companyName;

    // ИНН организации
    @Column(name = "inn")
    private Integer inn;

    // КПП организации
    @Column(name = "kpp")
    private Integer kpp;

    // ОГРН организации
    @Column(name = "ogrn")
    private Integer ogrn;

    // Номер рассчетного счета
    @Column(name = "bank_account")
    private Integer bankAccount;

    // Наименование банка
    @Column(name = "bank_name")
    private String bankName;

    // БИК организации
    @Column(name = "bik")
    private Integer bik;

    // Номер корреспондентского счета
    @Column(name = "correspondent_account")
    private Integer correspondentAccount;

    // Электронная почта организации
    @Column(name = "email")
    private String email;

    // Телефонный номер организации
    @Column(name = "phone_number")
    private String phoneNumber;
}
