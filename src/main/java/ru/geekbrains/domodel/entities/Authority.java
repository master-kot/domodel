package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Роль пользователя, варианты: ROLE_ADMIN - администратор сайта, ROLE_DIRECTOR - председатель СНТО,
    // ROLE_ACCOUNTANT - бухгалтер, ROLE_STAFF - персонал организации, ROLE_USER - пользователь сайта
    @Column(name = "authority", nullable = false)
    private String authority;

    @ManyToMany
    @JoinTable(name = "users_authorities",
            // Внешний ключ для Authority
            joinColumns = @JoinColumn(name = "authority_id"),
            // Внешний ключ для другой стороны, Authority в таблице users_authorities
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();
}
