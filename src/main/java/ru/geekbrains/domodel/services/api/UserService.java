package ru.geekbrains.domodel.services.api;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.UserRepresentation;

/**
 * Сервис пользователей
 */
@Service
public interface UserService extends UserDetailsService {

    User findByLogin(String login);

    User createNewUser(UserRepresentation request);

}
