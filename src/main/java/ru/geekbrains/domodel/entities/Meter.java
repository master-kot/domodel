package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    // Серийный номер счетчика
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    // Модель счетчика
    @Column(name = "model")
    private String model;

    // Дата плановой поверки счетчика
    @Column(name = "check_date")
    private LocalDate checkDate;

    // Лицевой счет, к которому прикреплен данный счетчик
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Тип счетчика
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private MeterType type;

    // Список показаний данного счетчика
    @OneToMany(mappedBy = "meter", cascade = CascadeType.REMOVE)
    private List<MeterData> meterDatas = new ArrayList<>();
}
