package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate creationDate;

    // Заголовок
    @Column(name = "title", nullable = false)
    private String title;

    // Полный текст (содержание)
    @Column(name = "full_text", nullable = false)
    private String fullText;

    // Адрес ссылки на картинку
    @Column(name = "picture_link")
    private String pictureLink;

    // Указатель публичности новости. Новость отображается для всех посетителей если false
    @Column(name = "hidden", nullable = false)
    private boolean hidden;

    // Указатель закрепления новости. Новость закреплена если true
    @Column(name = "pinned", nullable = false)
    private boolean pinned;

    // Указатель видимости новости. Новость отображается если true
    @Column(name = "visible", nullable = false)
    private boolean visible;

    // Автор
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User authorId;

    // Имя автора новости
    @Transient
    private String authorName;
}
