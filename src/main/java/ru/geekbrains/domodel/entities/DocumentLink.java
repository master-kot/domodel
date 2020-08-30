package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность ссылки на документ
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "document_links")
public class DocumentLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Ссылка на адрес документа
    @Column(name = "link_address", nullable = false)
    private String linkAddress;

    // Голосование, которому соответствует картинка
    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;
}
