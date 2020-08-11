package ru.geekbrains.domodel.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность показания счетчика
 */
@Data
@Entity
@Table(name = "meter_datas")
public class MeterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO Обратная ссылка на meter_id
    // Идентификатор счетчика (id в таблице meters)
//    @Column(name = "meter_id", nullable = false)
//    private Integer meterNumber;
    @ManyToOne
    @JoinColumn(name = "meter")
    private Meter meter;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "amount")
    private Double amount;

    public MeterData() {
    }
}
