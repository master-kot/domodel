package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность различных типов счетчиков.
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
    @Column(nullable = false, unique = true)
    private String description;

    // Единица измерения показаний счетчика
    @Column(nullable = false)
    private String measure;

    // Список счетчиков, для которых используется данный тариф
    @OneToMany(mappedBy = "type")
    private Set<Meter> meters = new HashSet<>();
}
