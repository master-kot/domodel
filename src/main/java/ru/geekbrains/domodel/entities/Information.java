package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность блока информации на странице О нас
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "information")
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Наименование блока
    @Column(name = "block_name", nullable = false)
    private String blockName;

    // Текст блока
    @Column(name = "text", nullable = false)
    private String text;

    // Указатель публичности блока. Если false, блок отображается для всех посетителей,
    // Если true, новость отображается только для зарегистрированных посетителей
    @Column(name = "hidden", nullable = false)
    private boolean hidden;
}
