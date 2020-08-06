package ru.geekbrains.domodel.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность дома
 */
@Entity
@Table(name = "houses")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "street")
    private String street;

    @Column(name = "number", nullable = false)
    private String number;

    //TODO создать таблицы
//    @OneToMany(mappedBy = "house")
//    private List<Meter> meters;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    //TODO создать таблицы
//    public List<Meter> getMeters() {
//        return meters;
//    }
//
//    public void setMeters(List<Meter> meters) {
//        this.meters = meters;
//    }

    public House() {
    }
}
