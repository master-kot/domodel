package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.UserService;
import ru.geekbrains.domodel.services.core.UserServiceImpl;
import ru.geekbrains.domodel.services.api.UserService;

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
@RequiredArgsConstructor
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

    // Указатель актуальности и видимости новости. Новость видна юзерам если true
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
