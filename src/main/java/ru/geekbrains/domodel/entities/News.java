package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
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

    public News(String title,
                String fullText,
                boolean hidden,
                boolean pinned,
                String pictureLink,
                User author_id) {
        this.creationDate = LocalDate.now();
        this.title = title;
        this.fullText = fullText;
        this.hidden = hidden;
        this.pinned = pinned;
        this.pictureLink = pictureLink;
        this.authorId = author_id;
        this.visible = true;

        int limit = 20;//TODO исправить на нужное значение
        if (fullText.length()>limit)
            this.shortText = fullText.substring(0,limit);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Дата создания
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    // Заголовок новости
    @Column(name = "title", nullable = false)
    private String title;

    // Краткий текст новости, ограниченный до 255 символов
    @Transient
    private String shortText;

    // Полный текст новости (содержание)
    @Column(name = "full_text", nullable = false)
    private String fullText;

    // Ссылка на адрес картинки новости
    @Column(name = "picture_link")
    private String pictureLink;

    // Ссылка на пользователя - автора данной новости
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User authorId;

    // Указатель публичности новости. Если false, новость отображается для всех посетителей,
    // Если true, новость отображается только для зарегистрированных посетителей
    @Column(name = "hidden", nullable = false)
    private boolean hidden;

    // Указатель закрепления новости. Новость закреплена если true
    @Column(name = "pinned", nullable = false)
    private boolean pinned;

    // Указатель актуальности и видимости новости. Новость видна юзерам если true
    @Column(name = "visible", nullable = false)
    private boolean visible;
}
