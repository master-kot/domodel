package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность обращения
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "appeals")
public class Appeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Пользователь - автор обращения
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User authorId;

    // Дата создания
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    // Заголовок (тема)
    @Column(name = "title", nullable = false)
    private String title;

    // Полный текст обращения
    @Column(name = "text", nullable = false)
    private String text;

    // Телефон для связи
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    // Статус выполнения обращения
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AppealStatus status;

    // Список ссылок на фотографии
    @OneToMany(mappedBy = "appeal", cascade = CascadeType.ALL)
    private List<PictureLink> pictureLinks = new ArrayList<>();
}
