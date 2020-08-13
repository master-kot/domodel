package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность данных показания счетчика
 */

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "meter_datas")
public class MeterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Счетчик, к которому прикреплено (соответствует) показание
    @ManyToOne
    @JoinColumn(name = "meter_id", nullable = false)
    private Meter meter;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "amount")
    private Double amount;
}
