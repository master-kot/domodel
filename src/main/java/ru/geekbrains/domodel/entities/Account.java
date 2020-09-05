package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность лицевого счета, заведенного для каждого дома
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Адрес объекта (может быть не заполнен)
    @Column(name = "address")
    private String address;

    // Номер дома или участка
    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    // Количество соток участка
    @Column(name = "acres_amount")
    private Double acresAmount;

    // Пользователь, к которому прикреплен данный лицевой счет
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    // Список счетчиков данного аккаунта
//    @OneToMany(mappedBy = "account")
//    private List<Meter> meters = new ArrayList<>();

//    // Список платежных документов (счетов), выставленных для данного аккаунта
//    @OneToMany(mappedBy = "account")
//    private List<Bill> bills = new ArrayList<>();
}
