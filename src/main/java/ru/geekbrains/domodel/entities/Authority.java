package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Сущность роли пользователя
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @Column(name = "authority", nullable = false)
    private String authority;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
