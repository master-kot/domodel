package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.UserRequest;
import ru.geekbrains.domodel.dto.PasswordRequest;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.UserService;

import java.util.HashMap;
import java.util.Map;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;

/**
 * Контроллер профиля пользователя
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    // Тип объекта
    private final String PRODUCE_TYPE = "application/json";

    // Список необходимых сервисов
    private final UserService userService;
    private final AccountService accountService;

    @ApiOperation(value = "Выводит профиль данного пользователя и список его лицевых счетов")
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

    @ApiOperation(value = "Изменяет профиль пользователя")
    @PostMapping(value = "/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              Authentication authentication) {
        if (authentication != null) {
            UserDto updatedUser = userService.update(userDto, authentication.getName());
            if (userDto != null) {
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Изменяет пароль пользователя")
    @PostMapping(value = "/update/password")
    public ResponseEntity<Boolean> updateUserPassword(@RequestBody PasswordRequest passwordRequest,
                                              Authentication authentication) {
        if (authentication != null) {
            boolean isPasswordUpdated = userService.updatePassword(passwordRequest, authentication);
            return new ResponseEntity<>(isPasswordUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Выводит профиль пользователя по его индексу. Только для роли Админа")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> readUserById(@PathVariable(name = "id") Long id,
                                                Authentication authentication){
        if (hasAuthenticationRoleAdmin(authentication)) {
            UserDto userDto = userService.getDtoById(id);
            if (userDto != null) {
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Создает нового пользователя. Только для роли Админа")
    @PostMapping(value = "/create", consumes = PRODUCE_TYPE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequest userRequest,
                                              Authentication authentication) {
        if (hasAuthenticationRoleAdmin(authentication)) {
            UserDto userDto = userService.save(userRequest);
            if (userDto != null) {
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Проверяет, что сделавший запрос пользователь имеет роль Админа
     */
    private boolean hasAuthenticationRoleAdmin(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_ADMIN)));
    }
}
