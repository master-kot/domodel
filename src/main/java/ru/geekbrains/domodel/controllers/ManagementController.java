package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.*;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.RequisitesService;
import ru.geekbrains.domodel.services.api.UserService;

import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.mappers.ResponseMapper.*;

/**
 * Контроллер управления сайтом
 */
@Api("Контроллер управления сайтом. Доступ только для Администратора")
@CrossOrigin
@RestController
@Secured(value = {ROLE_ADMIN})
@RequestMapping("/api/v1/management")
@RequiredArgsConstructor
public class ManagementController {

    private final String DATA_TYPE = "application/json";

    // Необходимые сервисы
    private final UserService userService;
    private final RequisitesService requisitesService;
    private final AccountService accountService;

    @ApiOperation(value = "Выводит текущие реквизиты компании")
    @GetMapping(value = "/requisites", produces = DATA_TYPE)
    public ResponseEntity<RequisitesDto> readCurrentRequisites() {
        return getDtoResponse(requisitesService.getCurrentDto());
    }

    @ApiOperation(value = "Создает текущие реквизиты компании, если не были созданы, либо изменяет текущие")
    @PostMapping(value = "/requisites", produces = DATA_TYPE)
    public ResponseEntity<RequisitesDto> updateRequisites(@RequestBody RequisitesDto requisitesDto) {
        return getDtoResponse(requisitesService.update(requisitesDto));
    }

    @ApiOperation(value = "Выводит список всех профилей пользователей")
    @GetMapping(value = "/users", produces = DATA_TYPE)
    public ResponseEntity<List<UserDto>> readAllUsers() {
        return getListUserDtoResponse(userService.getAll());
    }

    @ApiOperation(value = "Выводит профиль пользователя по его индексу")
    @GetMapping(value = "/users/{id}", produces = DATA_TYPE)
    public ResponseEntity<UserDto> readUserById(@PathVariable(name = "id") Long id){
        return getDtoResponse(userService.getDtoById(id));
    }

    @ApiOperation(value = "Изменяет профиль пользователя")
    @PostMapping(value = "/users/{id}", produces = DATA_TYPE)
    public ResponseEntity<UserDto> updateUserById(@PathVariable(name = "id") Long id,
                                                  @RequestBody UserDto userDto,
                                                  Authentication authentication) {
        return getDtoResponse(userService.update(userDto, authentication.getName()));
    }

    @ApiOperation(value = "Создает новый профиль пользователя")
    @PostMapping(value = "/users", produces = DATA_TYPE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequest userRequest) {
        return getDtoResponse(userService.save(userRequest));
    }

    @ApiOperation(value = "Удаляет профиль пользователя, возвращая результат удаления")
    @DeleteMapping(value = "/users/{id}", produces = DATA_TYPE)
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") long id) {
        return getBooleanResponse(userService.deleteById(id));
    }

    @ApiOperation(value = "Выводит список всех лицевых счетов")
    @GetMapping(value = "/accounts", produces = DATA_TYPE)
    public ResponseEntity<List<AccountDto>> readAllAccounts() {
        return getListAccountDtoResponse(accountService.getAllDto());
    }

    @ApiOperation(value = "Выводит лицевой счет по его индексу")
    @GetMapping(value = "/accounts/{id}", produces = DATA_TYPE)
    public ResponseEntity<AccountDto> readAccountById(@PathVariable(name = "id") Long id) {
        return getDtoResponse(accountService.getAccountDtoById(id));
    }

    @ApiOperation(value = "Изменяет лицевой счет")
    @PostMapping(value = "/accounts/{id}", produces = DATA_TYPE)
    public ResponseEntity<AccountDto> updateUser(@PathVariable(name = "id") Long id,
                                                 @RequestBody AccountDto accountDto) {
        return getDtoResponse(accountService.update(accountDto));
    }

    @ApiOperation(value = "Создает новый лицевой счет")
    @PostMapping(value = "/accounts", produces = DATA_TYPE)
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountRequest accountRequest) {
        return getDtoResponse(accountService.save(accountRequest));
    }
}
