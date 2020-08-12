package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность калькуляции (обоснования цены счета) для конкретного счета.
 * Все поля сущности рассчитываются отдельным модулем калькуляции.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "calculations")
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Ссылка на номер счета
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    // Ссылка на предыдущие показания счетчика
    @OneToOne
    @JoinColumn(name="previous_data")
    private MeterData previousData;

    // Ссылка на текущие показания счетчика
    @OneToOne
    @JoinColumn(name = "current_data")
    private MeterData currentData;

    // Количество - разница паказаний
    @Column(name = "amount")
    private Double amount;

    // Цена за единицу
    @Column(name = "price")
    private Double price;

    // Стоимость за все
    @Column(name = "cost")
    private Double cost;
}
