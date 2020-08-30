package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Сущность Голоса (данные единичного голосования)
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "vote_datas")
public class VoteData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Выбранный вариант ответа
    @Column(name = "option", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ChoiceOption option;

    // Пользователь, который проголосовал
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User authorId;

    // Опрос, которому соответствует голос
    @ManyToOne
    @JoinColumn(name = "vote_id", nullable = false)
    private Vote vote;
}
