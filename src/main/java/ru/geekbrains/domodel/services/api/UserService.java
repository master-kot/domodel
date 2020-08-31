package ru.geekbrains.domodel.services.api;

import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.dto.UserDto;

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
    User getUserById(Long userId);

    /**
     * Найти пользователя по логину
     *
     * @param username логин пользователя
     * @return пользователь
     */
    User getUserByUsername(String username);

    /**
     * Найти всех пользователей
     *
     * @return список пользователей
     */
    List<User> getAllUsers();

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
     * @param userDto отображение данных пользователя
     * @return новый пользователь, сохраненный в репозитории
     */
    User createUser(UserDto userDto);

    /**
     * Изменить данные пользователя
     *
     * @param userDto отображение данных пользователя
     * @param user изменяемый пользователь
     */
    User updateUser(UserDto userDto, User user);

    /**
     * Изменить данные пользователя
     *
     * @param userDto пользователь с измененными данными
     * @param username логин пользователя
     */
    void updateUser(UserDto userDto, String username);
}
