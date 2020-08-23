package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность должностного лица компании, используется в модуле информации о компани
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Должность
    @Column(name = "position")
    private Date position;

    // Информация о должностном лице
    @Column(name = "description")
    private String description;

    // Имя
    @Column(name = "first_name")
    private String firstName;

    // Фамилия
    @Column(name = "second_name")
    private String secondName;

    // Отчество
    @Column(name = "middle_name")
    private String middleName;

    // Телефонный номер
    @Column(name = "phone_number")
    private String phoneNumber;

    // Адрес сылки на фотографии
    @Column(name = "photo_link")
    private String photoLink;

    // Ссылка на информацию о компании
    @ManyToOne
    @JoinColumn(name = "contact_information")
    private Information contactInformation;

    // Ссылка на информацию о компании
    @ManyToOne
    @JoinColumn(name = "management_information")
    private Information managementInformation;

    // Ссылка на информацию о компании
    @ManyToOne
    @JoinColumn(name = "revision_information")
    private Information revisionInformation;
}
