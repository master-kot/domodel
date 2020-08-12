package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность единичных данных показания счетчика
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "meter_datas")
public class MeterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Счетчик, к которому прикреплено данное показание
    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "amount")
    private Double amount;
}
