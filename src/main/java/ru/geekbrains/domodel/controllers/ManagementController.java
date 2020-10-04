package ru.geekbrains.domodel.controllers;

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
import java.util.Random;

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

    @ApiOperation(value = "Создает лицевой счет")
    @PostMapping("/accounts/create")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountRequest accountRequest) {
        return getDtoResponse(accountService.save(accountRequest));
    }

    @ApiOperation(value = "Создает несколько пользователей")
    @PostMapping("/users/create/{number}")
    public ResponseEntity<List<UserDto>> createUsers(@PathVariable(name = "number") Integer number) {
        Random random = new Random(1000);
        for (int i = 0; i < number; i++) {
            String password = "pswd" + random.nextInt();
            UserRequest userRequest = new UserRequest();
            userRequest.setPassword(password);
            userRequest.setPasswordConfirm(password);
            userRequest.setUsername(String.valueOf(100_001 + i));
            UserDto userDto = userService.save(userRequest);
            if (userDto != null) {
                userDto.setFirstName(password);
                userService.update(userDto);
            }
        }
        return getListUserDtoResponse(userService.getAll());
    }

    @ApiOperation(value = "Создает несколько лицевых счетов")
    @PostMapping("/accounts/create/{number}")
    public ResponseEntity<List<AccountDto>> createAccounts(@PathVariable(name = "number") Integer number) {
        for (int i = 0; i < number; i++) {
            AccountRequest accountRequest = new AccountRequest();
            accountRequest.setHouseNumber(String.valueOf(i+1));
            AccountDto accountDto = accountService.save(accountRequest);

            UserDto userDto = userService.getByUsername(String.valueOf(100_001 + i));
            if (userDto != null) {
                accountDto.setUser(userDto);
                accountService.update(accountDto);
            }
        }
        return getListAccountDtoResponse(accountService.getAllDto());
    }
}
