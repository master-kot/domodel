package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность Опроса
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "votes")
public class Vote {

    // Идентификационный номер
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Тема Опроса (до 50 знаков)
    @Column(name = "title", nullable = false)
    private String title;

    // Предмет опроса (С учетом перехода текст до 500 знаков)
    @Column(name = "text", nullable = false)
    private String text;

    // Дата начала опроса (не должна быть меньше текущей даты)
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    // Дата завершения опроса (не должна быть меньше текущей даты и даты начала опроса)
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    // Список ссылок на фото (2 шт.), загружается при необходимости
    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL)
    private List<PictureLink> pictureLinks = new ArrayList<>();

    // Список ссылок на документы (2 шт.), загружается  при необходимости
    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL)
    private List<DocumentLink> documentLinks = new ArrayList<>();

    // Список голосов
    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL)
    private List<VoteData> voteDatas = new ArrayList<>();
}
