package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность тарифа для
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tariffs")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Описание данного тарифа (для более понятного выбора тарифа при назначении на счетчик)
    @Column(name = "description", nullable = false)
    private String description;

    // Цена за единицу
    @Column(name = "price")
    private Double price;

    // Список счетчиков, для которых используется данный тариф
    @OneToMany(mappedBy = "tariff")
    private Set<Meter> meters = new HashSet<>();

    // Значение по умолчанию, устанавливается в качестве показания за период, если показание не было подано
    // НЕ ИСПОЛЬЗУЕТСЯ В MVP 0
//    @Column(name = "default_increase_value")
//    private Double defaultIncreaseValue;
}
