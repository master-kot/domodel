package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность ссылки на картинку (фотографию)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "picture_links")
public class PictureLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Ссылка на адрес картинки
    @Column(name = "link_address", nullable = false)
    private String linkAddress;

    // Голосование, которому соответствует картинка
    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;

    // Голосование, которому соответствует картинка
    @ManyToOne
    @JoinColumn(name = "appeal_id")
    private Appeal appeal;
}
