package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность тарифа
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
    private Long id;

    // Цена за единицу
    @Column(name = "price")
    private Double price;

    // Тип счетчика, имеет значения: ELECTRICITY_UNIFIED,
    // ELECTRICITY_DAY, ELECTRICITY_NIGHT, GAS, HOT_WATER, COLD_WATER
    @Column(name = "type", nullable = false)
    private String type;

    // Список счетчиков, для которых используется данный тариф
    @OneToMany(mappedBy = "tariff")
    private Set<Meter> meters = new HashSet<>();
}
