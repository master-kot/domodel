package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

/**
 * Сущность счета (платежного документа).
 * Сумма к оплате total должна быть рассчитана модулем калькуляции или внесена вручную.
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

    // Ссылка на лицевой номер пользователя
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Дата создания
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    // Назначение платежа
    @Column(name = "target", nullable = false)
    private String target;

    // Сумма к оплате (сумма всех калькуляций)
    @Column(name = "total", nullable = false)
    private Double total;

    // true для счета, подсчитанного автоматически, false если сумма к оплате введена вручную
    @Column(name = "calculated", nullable = false)
    private boolean calculated;

    // TODO сделать в виде перечисления
    // Статус отправки счета пользователю может быть: NEW новая платежка, SENT отправлена пользователю,
    // RECEIVED просмотрена пользователем, CANCELED отменена (отозвана) председателем (бухгалтером)
    @Column(name = "send_status", nullable = false)
    private String sendStatus;

    // Статус платежа, изменяется председателем (бухгалтером)
    @Column(name = "payment_status", nullable = false)
    private boolean paymentStatus;

    // Ссылка на платежные реквизиты
    @ManyToOne
    @JoinColumn(name = "requisites_id")
    private Requisites requisites;

    // Ссылка на калькуляцию (обоснование цены счета)
    @OneToMany(mappedBy = "bill")
    private List<Calculation> calculations = new ArrayList<>();
}
