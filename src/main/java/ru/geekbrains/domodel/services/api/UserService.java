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

    /**
     * Найти пользователя по логину
     *
     * @param login логин пользователя
     * @return пользователь
     */
    User findByLogin(String login);

    /**
     * Создать нового пользователя
     *
     * @param request отображение пользователя
     * @return новый пользователь, сохраненный в репозитории
     */
    User createNewUser(UserRepresentation request);

}
