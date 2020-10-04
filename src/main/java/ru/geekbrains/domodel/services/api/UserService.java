package ru.geekbrains.domodel.services.api;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.UserRequest;
import ru.geekbrains.domodel.dto.PasswordRequest;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.entities.common.JwtUser;
import ru.geekbrains.domodel.entities.User;

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
    UserDto getDtoById(Long userId);

    /**
     * Найти пользователя по логину
     *
     * @param username логин пользователя
     * @return пользователь
     */
    UserDto getByUsername(String username);

    /**
     * Найти всех пользователей
     *
     * @return список пользователей
     */
    List<UserDto> getAll();

    /**
     * Удалить пользователя по его идентификатору
     *
     * @param userId идентификатор пользователя
     * @return удален ли пользователь
     */
    boolean deleteById(Long userId);

    /**
     * Создать нового пользователя
     *
     * @param userRequest отображение данных пользователя
     * @return новый пользователь, сохраненный в репозитории
     */
    UserDto save(UserRequest userRequest);

    /**
     * Изменить данные пользователя
     *
     * @param userDto пользователь с измененными данными
     * @param username логин пользователя
     */
    UserDto update(UserDto userDto, String username);

    /**
     * Изменить данные пользователя (для внутреннего использования)
     *
     * @param userDto пользователь с измененными данными
     */
    User update(UserDto userDto);

    /**
     * Получить общего Пользователя для межсервисного взаимодействия
     *
     * @param username логин пользователя
     * @return общая сущность Пользователя
     */
    User getUserByUsername(String username);

    /**
     * Получить Jwt Пользователя для генерации токенов
     *
     * @param username логин пользователя
     * @return общая сущность Пользователя
     */
    JwtUser getJwtUserByUsername(String username);

    /**
     * Изменить пароль пользователя
     *
     * @param passwordRequest данные для изменения пароля
     * @param authentication авторизация
     * @return измененен ли пользователь
     */
    boolean updatePassword(PasswordRequest passwordRequest, Authentication authentication);
}
