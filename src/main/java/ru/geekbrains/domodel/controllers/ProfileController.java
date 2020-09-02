package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.mappers.UserMapper;
import ru.geekbrains.domodel.services.api.UserService;

/**
 * Контроллер профиля пользователя
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    // Список необходимых сервисов
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.getUserById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = userMapper.userToUserDto(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
