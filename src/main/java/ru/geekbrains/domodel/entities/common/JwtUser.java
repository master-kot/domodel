package ru.geekbrains.domodel.entities.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.geekbrains.domodel.dto.MeterDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Spring Security обертка для класса Пользователя.
 */
@Setter
@NoArgsConstructor
public class JwtUser implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private boolean enabled;

    private List<JwtRole> authorities;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void addJwtRole(JwtRole jwtRole) {
        if (authorities == null) {
            authorities = new ArrayList<>();
        }
        authorities.add(jwtRole);
    }
}
