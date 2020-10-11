package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность различных типов счетчиков. Примеры заполнения:
 * "Счётчик электроэнергии (однотарифный)", "кВТ/час",
 * "Счётчик электроэнергии (дневной)", "кВТ/час",
 * "Счётчик электроэнергии (ночной)", "кВТ/час",
 * "Счётчик газа", "куб.метров",
 * "Счётчик горячей воды", "куб.метров",
 * "Счётчик холодной воды", "куб.метров"
 */
@Entity
@Setter
@Getter
@Table(name = "meter_type")
public class MeterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Описание счетчика
    @Column(name = "description", nullable = false, unique = true)
    private String description;

    // Единица измерения показаний счетчика
    @Column(name = "measure_unit", nullable = false)
    private String measureUnit;

    // Список счетчиков, для которых используется данный тариф
    @OneToMany(mappedBy = "type")
    private Set<Meter> meters = new HashSet<>();
}
