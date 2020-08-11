package ru.geekbrains.domodel.entities;

import lombok.Data;
import ru.geekbrains.domodel.entities.enums.MeterType;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность счетчика показаний. Если счетчик электричества двухтарифный,
 * необходимо создать два счетчика с разными type, но одним meterNumber
 */
@Data
@Entity
@Table(name = "meters")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_meter")
    private User user;

    @Column(name = "meter_number", nullable = false)
    private Integer meterNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private MeterType type;

    @OneToMany(mappedBy = "meter")
    private List<MeterData> meterDatas;

    //TODO Обратная ссылка на лицевой счет, нужна ли она?
//    @ManyToOne
//    @JoinColumn(name = "requisites", nullable = false)
//    private Account account;

    public Meter() {
    }
}
