package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    // Пароль
    @Column(name = "password", nullable = false)
    private String password;

    // Подтверждение пароля
    @Transient
    private String passwordConfirm;

    // Пользователь активен (true) или заблокирован (false)
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    // Список ролей пользователя
    @ManyToMany(fetch = EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_authorities",
            // Внешний ключ для User в в таблице users_authorities
            joinColumns = @JoinColumn(name = "user_id"),
            // Внешний ключ для другой стороны, User в таблице users_authorities
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities = new ArrayList<>();

    // Дата создания
    @Column(name = "creation_date")
    private LocalDate creationDate;

    // Имя
    @Column(name = "first_name")
    private String firstName;

    // Фамилия
    @Column(name = "second_name")
    private String lastName;

    // Отчество
    @Column(name = "middle_name")
    private String patronymic;

    // Адрес электронной почты
    @Column(name = "email", unique = true)
    private String email;

    // Ссылка на адрес фотографии
    @Column(name = "photo_link")
    private String photoLink;

    // Почтовый адрес
    @Column(name = "address")
    private String address;

    // Обо мне
    @Column(name = "about")
    private String about;

    // Список лицевых счетов пользователя
    @OneToMany(mappedBy = "user")
    private Set<Account> accounts = new HashSet<>();

    public User(String username,
                String password,
                boolean enabled,
                LocalDate creationDate) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.creationDate = creationDate;
    }
}
