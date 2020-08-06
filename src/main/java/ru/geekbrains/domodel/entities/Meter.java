package ru.geekbrains.domodel.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность счетчика
 */
@Entity
@Table(name = "meters")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private House house;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "type", nullable = false)
    private String type;

    //TODO создать таблицы
//    @OneToMany(mappedBy = "meter")
//    private List<Integer> datas;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //TODO создать таблицы
//    public List<Integer> getDatas() {
//        return datas;
//    }
//
//    public void setDatas(List<Integer> datas) {
//        this.datas = datas;
//    }

    public Meter() {
    }
}
