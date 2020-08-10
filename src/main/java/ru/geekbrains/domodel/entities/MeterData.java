package ru.geekbrains.domodel.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность показания счетчика
 */
@Entity
@Table(name = "meter_datas")
public class MeterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //TODO Обратная ссылка на meter_id
    // Идентификатор счетчика (id в таблице meters)
    @Column(name = "meter_id", nullable = false)
    private Integer meterNumber;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "amount")
    private Double amount;

    //TODO геттеры и сеттеры для полей класса, обновить файл создания базы данных

    public MeterData() {
    }
}
