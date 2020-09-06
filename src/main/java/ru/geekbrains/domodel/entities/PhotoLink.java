package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность ссылки на фотографию
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "photo_links")
public class PhotoLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Заголовок
    @Column(name = "title", nullable = false)
    private String title;

    // Ссылка на адрес фотографии
    @Column(name = "link_address", nullable = false)
    private String linkAddress;

    public PhotoLink(String linkAddress) {
        this.linkAddress = linkAddress;
    }
}
