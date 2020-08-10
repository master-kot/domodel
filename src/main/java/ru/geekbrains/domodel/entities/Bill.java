package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность счета (платежного документа)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
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

    //TODO обновить файл создания базы данных
}
