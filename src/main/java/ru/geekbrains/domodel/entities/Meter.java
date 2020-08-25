package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.geekbrains.domodel.entities.constants.MeterType;

import javax.persistence.*;
import java.util.*;

/**
 * Сущность счетчика показаний. Если счетчик электричества двухтарифный,
 * необходимо создать два счетчика с разными type, но одним meterNumber.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "meters")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Лицевой счет, к которому прикреплен данный счетчик
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Серийный номер счетчика
    @Column(name = "serial_number", nullable = false)
    private Integer serialNumber;

    @Column(name = "model", length = 50)
    private String model;

    // Дата поверки счетчика
    @Column(name = "check_date")
    private Date checkDate;

    // Тип счетчика, содержит его описание и единицу измерения
    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private MeterType type;

    // Ссылка на рассчетный тариф для счетчика
    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    // Список показаний данного счетчика
    @OneToMany(mappedBy = "meter")
    private List<MeterData> meterDatas = new ArrayList<>();
}
