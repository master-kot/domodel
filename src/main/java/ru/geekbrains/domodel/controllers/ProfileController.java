package ru.geekbrains.domodel.controllers;

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

import java.util.HashMap;
import java.util.Map;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;
import static ru.geekbrains.domodel.mappers.ResponseMapper.getBooleanResponse;
import static ru.geekbrains.domodel.mappers.ResponseMapper.getDtoResponse;

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
    private final AccountService accountService;

    @ApiOperation(value = "Выводит профиль текущего пользователя и список его лицевых счетов")
    @GetMapping(value = "")
    public ResponseEntity<Map<Object, Object>> readUser(Authentication authentication){
        if (authentication != null) {
            UserDto userDto = userService.getByUsername(authentication.getName());
            if (userDto != null) {
                Map<Object, Object> response = new HashMap<>();
                response.put("user", userDto);
                response.put("accounts", accountService.getAllDtoByUserUsername(authentication.getName()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @ApiOperation(value = "Изменяет профиль текущего пользователя")
    @PostMapping(value = "/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              Authentication authentication) {
        return getDtoResponse(userService.update(userDto, authentication.getName()));
    }

    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @ApiOperation(value = "Изменяет пароль текущего пользователя")
    @PostMapping(value = "/update/password")
    public ResponseEntity<Boolean> updateUserPassword(@RequestBody PasswordRequest passwordRequest,
                                                      Authentication authentication) {
        return getBooleanResponse(userService.updatePassword(passwordRequest, authentication));
    }
}
