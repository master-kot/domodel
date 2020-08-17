package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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

    // Счетчик, которому соответствует данное показание
    @ManyToOne
    @JoinColumn(name = "meter_id", nullable = false)
    private Meter meter;

    // Дата создания показания
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    // Количество единиц (значение показания счетчика)
    @Column(name = "value", nullable = false)
    private Double value;
    
    // Показания автоматически сгенерированы по среднему нормативу (true) или поданы пользователем (false)
    @Column(name = "autogenerated")
    private boolean autogenerated;
}
