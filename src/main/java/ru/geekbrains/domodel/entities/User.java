package ru.geekbrains.domodel.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

/**
 * Сущность пользователя
 */
@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

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
