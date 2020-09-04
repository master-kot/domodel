package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.UserDto;
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

    @ApiOperation(value = "Выводит профиль пользователя по его индексу")
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> readUserById(@PathVariable(name = "id") Long id){
        UserDto user = userService.getUserById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
