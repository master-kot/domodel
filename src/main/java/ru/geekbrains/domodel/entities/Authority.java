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

    // Роль пользователя, варианты: ROLE_ADMIN - администратор сайта, ROLE_DIRECTOR - председатель СНТО,
    // ROLE_ACCOUNTANT - бухгалтер, ROLE_STAFF - персонал организации, ROLE_USER - пользователь сайта
    @Id
    @Column(name = "authority", nullable = false)
    private String authority;

    // Ссылка на пользователя, которому соответствует роль
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
