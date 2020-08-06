package ru.geekbrains.domodel.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Сущность роли пользователя
 */
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Authority() {
    }

    public Authority(User user, String authority) {
        this.user = user;
        this.authority = authority;
    }
}
