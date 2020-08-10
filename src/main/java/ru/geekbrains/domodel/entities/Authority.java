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
    @Column(name = "authority", nullable = false)
    private String authority;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
}
