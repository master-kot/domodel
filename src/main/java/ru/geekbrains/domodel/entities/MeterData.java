package ru.geekbrains.domodel.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность данных показания счетчика
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "meter_datas")
public class MeterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Счетчик, к которому прикреплено (соответствует) показание
    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "amount")
    private Double amount;
}
