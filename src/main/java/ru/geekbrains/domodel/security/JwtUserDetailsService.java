package ru.geekbrains.domodel.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.security.jwt.JwtUser;
import ru.geekbrains.domodel.security.jwt.JwtUserFactory;
import ru.geekbrains.domodel.services.api.UserService;

/**
 * Сервис, отвечающий за авторизацию пользователей.
 */
@Service
@Log4j2
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Пользователь с именем: " + username + " не найден");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("Пользователь с именем: {} успешно загружен", username);
        return jwtUser;
    }
}
