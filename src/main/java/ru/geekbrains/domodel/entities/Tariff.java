package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность тарифа для счетчиков показаний
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

    // Описание тарифа (для более понятного выбора тарифа при назначении на счетчик)
    @Column(name = "description", nullable = false)
    private String description;

    // Цена за единицу
    @Column(name = "price", nullable = false)
    private Double price;

    // Значение по умолчанию, устанавливается в качестве показания за период, если показание не было подано
    // НЕ ИСПОЛЬЗУЕТСЯ В MVP 0
//    @Column(name = "default_increase_value")
//    private Double defaultIncreaseValue;
}
