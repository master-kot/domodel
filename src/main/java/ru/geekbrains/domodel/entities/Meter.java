package ru.geekbrains.domodel.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Сущность счетчика показаний. Если счетчик электричества двухтарифный,
 * необходимо создать два счетчика с разными type, но одним meterNumber
 */
@Entity
@Data
@Table(name = "meters")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Серийный номер счетчика
    @Column(name = "meter_number", nullable = false)
    private Integer meterNumber;

    // Тип счетчика, значения: ELECTRICITY_UNIFIED, ELECTRICITY_DAY, ELECTRICITY_NIGHT, GAS, HOT_WATER, COLD_WATER
    @Column(name = "type", nullable = false)
    private String type;

    //TODO Ссылка на таблицу со списком показаний счетчиков
//    @OneToMany(mappedBy = "meter")
//    private List<MeterData> meterDatas;

    //TODO Обратная ссылка на лицевой счет, нужна ли она?
//    @ManyToOne
//    @JoinColumn(name = "requisites", nullable = false)
//    private Account account;

    //TODO обновить файл создания базы данных
}
