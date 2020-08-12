package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

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

    // Пользователь, к которому прикреплен данный лицевой счет
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "street")
    private String street;

    // Номер дома, участка
    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    // Список счетчиков данного аккаунта
    @OneToMany(mappedBy = "account")
    private Set<Meter> meters = new HashSet<>();

    // Список счетов, выставленных для данного аккаунта
    @OneToMany(mappedBy = "account")
    private Set<Bill> bills = new HashSet<>();
}
