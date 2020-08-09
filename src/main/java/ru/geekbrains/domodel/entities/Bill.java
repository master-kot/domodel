package ru.geekbrains.domodel.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность счета (платежного документа)
 */
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "calculated", nullable = false)
    private boolean calculated;

    @Column(name = "send_status", nullable = false)
    private String sendStatus;

    @Column(name = "payment_status", nullable = false)
    private boolean paymentStatus;

    //TODO ссылка на номер лицевого счета
//    @ManyToOne
//    @JoinColumn(name = "account_id", nullable = false)
//    private Account accountId;

    //TODO ссылка на номер реквизитов компании
//    @ManyToOne
//    @JoinColumn(name = "requisites", nullable = false)
//    private Requisites requisites;

    //TODO геттеры и сеттеры для полей класса, обновить файл создания базы данных

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Bill() {
    }
}
