package ru.geekbrains.domodel.services.api;

import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.UserRepresentation;

import java.util.List;

/**
 * Сервис пользователей
 */
@Service
public interface UserService {

    /**
     * Найти пользователя по его идентификатору
     *
     * @param userId идентификатор пользователя
     * @return пользователь
     */
    User findUserById(Long userId);

    /**
     * Найти пользователя по логину
     *
     * @param username логин пользователя
     * @return пользователь
     */
    User findUserByUsername(String username);

    /**
     * Найти всех пользователей
     *
     * @return список пользователей
     */
    List<User> findAllUsers();

    /**
     * Удалить пользователя по его идентификатору
     *
     * @param userId идентификатор пользователя
     * @return удален ли пользователь
     */
    boolean deleteUserById(Long userId);

    /**
     * Создать нового пользователя
     *
     * @param userData отображение данных пользователя
     * @return новый пользователь, сохраненный в репозитории
     */
    User createUser(UserRepresentation userData);

    /**
     * Изменить данные пользователя
     *
     * @param userData отображение данных пользователя
     * @param user изменяемый пользователь
     */
    User updateUser(UserRepresentation userData, User user);
}
