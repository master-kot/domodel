package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.dto.RequisitesDto;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.dto.UserRequest;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.RequisitesService;
import ru.geekbrains.domodel.services.api.UserService;

import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.mappers.ResponseMapper.*;

/**
 * Контроллер управления сайтом
 */
@ApiOperation("Контроллер управления сайтом. Доступ только для Администратора")
@CrossOrigin
@RestController
@Secured(value = {ROLE_ADMIN})
@RequestMapping("/api/v1/management")
@RequiredArgsConstructor
public class ManagementController {

    private final String CONSUME_TYPE = "application/json";

    // Необходимые сервисы
    private final UserService userService;
    private final RequisitesService requisitesService;
    private final AccountService accountService;

    @ApiOperation(value = "Выводит текущие реквизиты компании")
    @GetMapping("/requisites")
    public ResponseEntity<RequisitesDto> readCurrentRequisites() {
        return getDtoResponse(requisitesService.getCurrentDto());
    }

    @ApiOperation(value = "Создает текущие реквизиты компании, если не были созданы, либо изменяет текущие")
    @PostMapping(value = "/requisites", consumes = CONSUME_TYPE)
    public ResponseEntity<RequisitesDto> updateRequisites(@RequestBody RequisitesDto requisitesDto) {
        return getDtoResponse(requisitesService.update(requisitesDto));
    }

    @ApiOperation(value = "Выводит список всех пользователей")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> readAllUsers() {
        return getListUserDtoResponse(userService.getAll());
    }

    @ApiOperation(value = "Выводит профиль пользователя по его индексу")
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> readUserById(@PathVariable(name = "id") Long id){
        return getDtoResponse(userService.getDtoById(id));
    }

    @ApiOperation(value = "Изменяет профиль пользователя")
    @PostMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              Authentication authentication) {
        return getDtoResponse(userService.update(userDto, authentication.getName()));
    }

    @ApiOperation(value = "Выводит результат удаления пользователя")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") long id) {
        return getBooleanResponse(userService.deleteById(id));
    }

    @ApiOperation(value = "Создает нового пользователя")
    @PostMapping(value = "/users/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequest userRequest) {
        return getDtoResponse(userService.save(userRequest));
    }

    @ApiOperation(value = "Выводит список всех лицевых счетов")
    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return getListAccountDtoResponse(accountService.getAllDto());
    }
}
