package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name = "login", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(fetch = EAGER,
            mappedBy = "user",
            cascade = CascadeType.ALL, //удаление ролей каскадом вслед за удалением пользователя
            orphanRemoval = true) //удаление ролей не связанных ни с одним пользователем
    private List<Authority> authorities;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email", unique = true)
    private String email;

    //TODO нужна ли эта ссылка на аккаунты?
//    @OneToMany(mappedBy = "user")
//    private List<Account> accounts;

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
