package ru.geekbrains.domodel.entities;

import javax.persistence.*;

/**
 * Сущность реквизитов компании.
 * Используются для отображения информации о компании и формирования платежек
 */
@Entity
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Integer companyName) {
        this.companyName = companyName;
    }

    public Integer getInn() {
        return inn;
    }

    public void setInn(Integer inn) {
        this.inn = inn;
    }

    public Integer getKpp() {
        return kpp;
    }

    public void setKpp(Integer kpp) {
        this.kpp = kpp;
    }

    public Integer getOgrn() {
        return ogrn;
    }

    public void setOgrn(Integer ogrn) {
        this.ogrn = ogrn;
    }

    public Integer getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Integer bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getBik() {
        return bik;
    }

    public void setBik(Integer bik) {
        this.bik = bik;
    }

    public Integer getCorrespondentAccount() {
        return correspondentAccount;
    }

    public void setCorrespondentAccount(Integer correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Requisites() {
    }
}
