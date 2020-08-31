package ru.geekbrains.domodel.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Authority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Spring Security wrapper класса User.
 */
public class JwtUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final boolean enabled;
    private final List<Authority> authorities;
    private final LocalDate creationDate;
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String email;
    private final String photoLink;
    private final String address;
    private final Set<Account> accounts;

    public JwtUser(Long id,
                   String username,
                   String password,
                   boolean enabled,
                   List<Authority> authorities,
                   LocalDate creationDate,
                   String firstName,
                   String lastName,
                   String patronymic,
                   String email,
                   String photoLink,
                   String address,
                   Set<Account> accounts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
        this.creationDate = creationDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.photoLink = photoLink;
        this.address = address;
        this.accounts = accounts;
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

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public String getAddress() {
        return address;
    }

    public Set<Account> getAccounts() {
        return accounts;
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

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
