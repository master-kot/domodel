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

    @Column(name = "inn")
    private Integer inn;

    @Column(name = "kpp")
    private Integer kpp;

    @Column(name = "ogrn")
    private Integer ogrn;

    // Номер рассчетного счета
    @Column(name = "bank_account")
    private Integer bankAccount;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bik")
    private Integer bik;

    // Номер корреспондентского счета
    @Column(name = "correspondent_account")
    private Integer correspondentAccount;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
}
