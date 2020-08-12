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

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "short_text", nullable = false)
    private String shortText;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "visible", nullable = false)
    private boolean visible;

    @Column(name = "picture")
    private String picture;

    @Column(name = "type")
    private String type;

    // Ссылка на пользователя - автора новости
    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;
}