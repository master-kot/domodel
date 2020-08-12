package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.EAGER;

/**
 * Сущность пользователя
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Логин (телефон пользователя)
    @Column(name = "login", nullable = false, unique = true)
    private String username;

    // Пароль
    @Column(name = "password", nullable = false)
    private String password;

    // Пользователь активен (true) или заблокирован (false)
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    // Список ролей данного пользователя
    @OneToMany(fetch = EAGER,
            mappedBy = "user",
            cascade = CascadeType.ALL, //удаление ролей каскадом вслед за удалением пользователя
            orphanRemoval = true) //удаление ролей не связанных ни с одним пользователем
    private List<Authority> authorities = new ArrayList<>();

    // Дата создания
    @Column(name = "creation_date")
    private Date creationDate;

    // Имя
    @Column(name = "first_name")
    private String firstName;

    // Фамилия
    @Column(name = "second_name")
    private String secondName;

    // Отчество
    @Column(name = "middle_name")
    private String middleName;

    // Адрес электроной почты
    @Column(name = "email", unique = true)
    private String email;

    // Список лицевых счетов пользователя
    @OneToMany(mappedBy = "user")
    private Set<Account> accounts = new HashSet<>();

    public User(String username,
                String password,
                boolean enabled,
                Date creationDate) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.creationDate = creationDate;
    }
}
