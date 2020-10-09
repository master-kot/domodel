package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность Документ
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Заголовок (наименование)
    @Column(name = "title")
    private String title;

    // Ссылка на адрес документа
    @Column(name = "link_address", nullable = false)
    private String linkAddress;

    // Голосование, которому соответствует документ
    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;
}
