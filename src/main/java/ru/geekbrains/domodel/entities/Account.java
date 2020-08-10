package ru.geekbrains.domodel.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность лицевого счета, заведенного для каждого дома
 */
@Entity
@Data
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    //TODO ссылка на таблицу с данными счетчиков
//    @OneToMany(mappedBy = "account")
//    private List<Meter> meters;

    //TODO нужна ли обратная ссылка на пользователя?

    //TODO обновить файл создания базы данных
}
