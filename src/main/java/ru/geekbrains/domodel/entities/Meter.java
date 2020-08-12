package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность счетчика показаний. Если счетчик электричества двухтарифный,
 * необходимо создать два счетчика с разными type, но одним meterNumber
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "meters")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Лицевой счет, к которому прикреплен данный счетчик
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Серийный номер счетчика
    @Column(name = "meter_number", nullable = false)
    private Integer meterNumber;

    // Дата последней поверки счетчика
    @Column(name = "check_date")
    private Date checkDate;

    // Тип счетчика, имеет значения: ELECTRICITY_UNIFIED,
    // ELECTRICITY_DAY, ELECTRICITY_NIGHT, GAS, HOT_WATER, COLD_WATER
    @Column(name = "type", nullable = false)
    private String type;

    // Ссылка на рассчетный тариф для счетчика
    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    // Список показаний данного счетчика
    @OneToMany(mappedBy = "meter")
    private Set<MeterData> meterDatas = new HashSet<>();
}
