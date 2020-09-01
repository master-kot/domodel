package ru.geekbrains.domodel.security.jwt;

import ru.geekbrains.domodel.entities.User;

/**
 * Фабрика класса JwtUser.
 */
public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.getAuthorities(),
                user.getCreationDate(),
                user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getEmail(),
                user.getPhotoLink(),
                user.getAddress(),
                user.getAccounts()
        );
    }
}
