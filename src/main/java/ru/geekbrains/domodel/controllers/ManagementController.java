package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.*;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.RequisitesService;
import ru.geekbrains.domodel.services.api.UserService;

import javax.validation.Valid;
import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;

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
        return new ResponseEntity<>(requisitesService.getCurrentDto(), HttpStatus.OK);
    }

    @ApiOperation(value = "Создает текущие реквизиты компании, если не были созданы, либо изменяет текущие")
    @PostMapping(value = "/requisites", produces = DATA_TYPE)
    public ResponseEntity<RequisitesDto> updateRequisites(@Valid @RequestBody RequisitesDto requisitesDto) {
        return new ResponseEntity<>(requisitesService.update(requisitesDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Выводит список всех профилей пользователей")
    @GetMapping(value = "/users", produces = DATA_TYPE)
    public ResponseEntity<List<UserDto>> readAllUsers() {
        return new ResponseEntity<>(userService.getAllDto(), HttpStatus.OK);
    }

    @ApiOperation(value = "Выводит профиль пользователя по его индексу")
    @GetMapping(value = "/users/{id}", produces = DATA_TYPE)
    public ResponseEntity<UserDto> readUserById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(userService.getDtoById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Изменяет профиль пользователя")
    @PostMapping(value = "/users/{id}", produces = DATA_TYPE)
    public ResponseEntity<UserDto> updateUserById(@PathVariable(name = "id") Long id,
                                                  @Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.update(userDto, id), HttpStatus.OK);
    }

    @ApiOperation(value = "Создает новый профиль пользователя")
    @PostMapping(value = "/users", produces = DATA_TYPE)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.save(userRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Удаляет профиль пользователя, возвращая результат удаления")
    @DeleteMapping(value = "/users/{id}", produces = DATA_TYPE)
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Выводит список всех лицевых счетов")
    @GetMapping(value = "/accounts", produces = DATA_TYPE)
    public ResponseEntity<List<AccountDto>> readAllAccounts() {
        return new ResponseEntity<>(accountService.getAllDto(), HttpStatus.OK);
    }

    @ApiOperation(value = "Выводит лицевой счет по его индексу")
    @GetMapping(value = "/accounts/{id}", produces = DATA_TYPE)
    public ResponseEntity<AccountDto> readAccountById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(accountService.getAccountDtoById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Изменяет лицевой счет")
    @PostMapping(value = "/accounts/{id}", produces = DATA_TYPE)
    public ResponseEntity<AccountDto> updateUser(@PathVariable(name = "id") Long id,
                                                 @Valid @RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.update(accountDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Создает новый лицевой счет")
    @PostMapping(value = "/accounts", produces = DATA_TYPE)
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<>(accountService.save(accountRequest), HttpStatus.OK);
    }
}
