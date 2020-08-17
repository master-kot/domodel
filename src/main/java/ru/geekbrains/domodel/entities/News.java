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

    // Краткий текст новости (заголовок)
    @Column(name = "short_text", nullable = false)
    private String shortText;

    // Полный текст новости (содержание)
    @Column(name = "full_text", nullable = false)
    private String fullText;

    // Ссылка на адрес картинки новости
    @Column(name = "picture_link")
    private String pictureLink;

    // TODO есть ли смысл в этом поле?
    // Обображается (true) ли новость на сайте или нет (false)
    @Column(name = "visible", nullable = false)
    private boolean visible;

    // TODO переделать в виде перечисления
    // Тип новости
    @Column(name = "type")
    private String type;

    // Ссылка на пользователя - автора данной новости
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User authorId;
}
