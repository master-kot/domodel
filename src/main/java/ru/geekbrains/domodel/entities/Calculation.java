package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность калькуляции для каждой конкретной строки.
 * Является обоснованием цены в виде отдельной строки в счете за каждую отдельную услугу.
 * Все поля сущности заполняется при формировании платежного документа.
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

    // Количество единиц - разница паказаний
    @Column(name = "amount")
    private Double amount;

    // Цена за единицу
    @Column(name = "price")
    private Double price;

    // Стоимость за все потребленные единицы
    @Column(name = "cost")
    private Double cost;

    // НЕ ИСПОЛЬЗУЕТСЯ В MVP 0
    // true если калькуляция рассчитана по показаниям, false - если по нормативному значению
    @Transient
    private boolean calculated;
}
