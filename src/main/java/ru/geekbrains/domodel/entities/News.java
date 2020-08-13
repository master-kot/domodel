package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность новости для связи ее с БД
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Дата создания
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    // Краткий (основной текст) новости
    @Column(name = "short_text", nullable = false)
    private String shortText;

    // Полный текст новости
    @Column(name = "text", nullable = false)
    private String text;

    // Обображается (true) ли новость на сайте или нет (false)
    @Column(name = "visible", nullable = false)
    private boolean visible;

    // Ссылка на адрес картинки новости
    @Column(name = "picture")
    private String picture;

    // Тип новости
    @Column(name = "type")
    private String type;

    // Ссылка на пользователя - автора данной новости
    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;
}
