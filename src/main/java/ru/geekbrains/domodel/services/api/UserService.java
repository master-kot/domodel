package ru.geekbrains.domodel.services.api;

import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.NewUserDataDto;
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
    UserDto getById(Long userId);

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
     * @param newUserDataDto отображение данных пользователя
     * @return новый пользователь, сохраненный в репозитории
     */
    UserDto save(NewUserDataDto newUserDataDto);

    /**
     * Изменить данные пользователя
     *
     * @param userDto пользователь с измененными данными
     * @param username логин пользователя
     */
    void update(UserDto userDto, String username);

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
}
