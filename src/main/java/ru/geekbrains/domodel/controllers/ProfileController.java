package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.PasswordRequest;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;

/**
 * Контроллер профиля пользователя
 */
@Api("Контроллер профиля пользователя. Доступ только для зарегистрированных пользователей и Администратора")
@CrossOrigin
@RestController
@Secured(value = {ROLE_USER, ROLE_ADMIN})
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    // Тип данных
    private final String DATA_TYPE = "application/json";

    // Список необходимых сервисов
    private final UserService userService;
    private final AccountService accountService;

    @ApiOperation(value = "Выводит профиль текущего пользователя и список его лицевых счетов")
    @GetMapping(value = "", produces = DATA_TYPE)
    public ResponseEntity<Map<Object, Object>> readUser(Authentication authentication) {
        UserDto userDto = userService.getDtoByUsername(authentication.getName());
        Map<Object, Object> response = new HashMap<>();
        response.put("user", userDto);
        response.put("accounts", accountService.getAllDtoByUserUsername(authentication.getName()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Изменяет профиль текущего пользователя")
    @PostMapping(value = "/update", produces = DATA_TYPE)
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
                                              Authentication authentication) {
        return new ResponseEntity<>(userService.update(userDto, authentication.getName()), HttpStatus.OK);
    }

    @ApiOperation(value = "Изменяет пароль текущего пользователя")
    @PostMapping(value = "/update/password", produces = DATA_TYPE)
    public ResponseEntity<Boolean> updateUserPassword(@Valid @RequestBody PasswordRequest passwordRequest,
                                                      Authentication authentication) {
        return new ResponseEntity<>(userService.updatePassword(passwordRequest, authentication), HttpStatus.OK);
    }
}
