package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность информации о компании
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "information")
public class Information {

    @Id
    @Column(name = "id")
    private Integer id;

    // Реквизиты компании
    @OneToOne
    @JoinColumn(name="requisites")
    private Requisites requisites;

    // Список должностных лиц, видимых зарегистрированным пользователям на странице КОНТАКТЫ
    @OneToMany(mappedBy = "contactInformation")
    private List<Person> contactPersons = new ArrayList<>();

    // Список должностных лиц, отображаемых на странице О НАС в разделе РУКОВОДСТВО
    @OneToMany(mappedBy = "managementInformation")
    private List<Person> managementPersons = new ArrayList<>();

    // Список должностных лиц, отображаемых на странице О НАС в разделе РЕВИЗИОННАЯ КОМИССИЯ
    @OneToMany(mappedBy = "revisionInformation")
    private List<Person> revisionPersons = new ArrayList<>();
}
