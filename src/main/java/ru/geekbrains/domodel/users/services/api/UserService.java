package ru.geekbrains.domodel.users.services.api;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.users.entities.User;
import ru.geekbrains.domodel.users.entities.UserRepresentation;

/**
 * Сервис пользователей
 */
@Service
public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    User createNewUser(UserRepresentation request);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
