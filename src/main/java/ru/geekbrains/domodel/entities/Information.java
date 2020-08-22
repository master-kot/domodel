package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность информации о компании
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "information")
public class Information {

    // Реквизиты компании
    @OneToOne
    @JoinColumn(name="requisites")
    private Requisites requisites;
}
