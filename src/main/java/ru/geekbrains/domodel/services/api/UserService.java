package ru.geekbrains.domodel.services.api;

import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.NewUserDataDto;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.entities.common.JwtUser;
import ru.geekbrains.domodel.entities.common.UserCommon;

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
    UserDto getUserById(Long userId);

    /**
     * Найти пользователя по логину
     *
     * @param username логин пользователя
     * @return пользователь
     */
    UserDto getUserByUsername(String username);

    /**
     * Получить общего Пользователя для межсервисного взаимодействия
     *
     * @param username логин пользователя
     * @return общая сущность Пользователя
     */
    UserCommon getUserCommonByUsername(String username);

    /**
     * Получить Jwt Пользователя для генерации токенов
     *
     * @param username логин пользователя
     * @return общая сущность Пользователя
     */
    JwtUser getJwtUserByUsername(String username);

    /**
     * Найти всех пользователей
     *
     * @return список пользователей
     */
    List<UserDto> getAllUsers();

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
     * @param newUserDataDto отображение данных пользователя
     * @return новый пользователь, сохраненный в репозитории
     */
    UserDto saveUser(NewUserDataDto newUserDataDto);

    /**
     * Изменить данные пользователя
     *
     * @param userDto пользователь с измененными данными
     * @param username логин пользователя
     */
    void updateUser(UserDto userDto, String username);
}
