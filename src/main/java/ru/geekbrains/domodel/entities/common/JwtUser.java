package ru.geekbrains.domodel.entities.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Authority;

import java.util.Collection;
import java.util.List;

/**
 * Spring Security обертка для класса Пользователя.
 */
public class JwtUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final boolean enabled;
    private final List<JwtRole> authorities;
    private final List<Long> accountIds;

    public JwtUser(Long id,
                   String username,
                   String password,
                   boolean enabled,
                   List<JwtRole> authorities,
                   List<Long> accountIds) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
        this.accountIds = accountIds;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    public List<Long> getAccountIds() {
        return accountIds;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
