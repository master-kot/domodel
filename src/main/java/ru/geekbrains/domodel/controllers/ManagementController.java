package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.dto.RequisitesDto;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.RequisitesService;
import ru.geekbrains.domodel.services.api.UserService;

import java.util.List;

/**
 * Контроллер модуля управления сайтом
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/management")
@RequiredArgsConstructor
public class ManagementController {

    // Необходимые сервисы
    private final UserService userService;
    private final RequisitesService requisitesService;
    private final AccountService accountService;

    @ApiOperation(value = "Выводит текущие реквизиты компании")
    @GetMapping("/requisites")
    public ResponseEntity<RequisitesDto> getRequisitesPage() {
        return getDtoResponseEntity(requisitesService.getCurrentDto());
    }

//    @ApiOperation(value = "Выводит список всех пользователей")
//    @GetMapping("/users")
//    public List<UserDto> readAllUsers() {
//        return userService.getAll();
//    }

    @ApiOperation(value = "Выводит результат удаления пользователя")
    @DeleteMapping("/management/users/delete/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") long id) {
        return !userService.deleteById(id) ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Выводитсписок лицевых счетов ")
    @GetMapping("/accounts")
    public List<AccountDto> getAccounts() {
        return accountService.getAllDto();
    }

    /**
     * Формирует необходимый ответ в зависимости от содержания списка objectDtoList
     */
    private ResponseEntity<List<RequisitesDto>> getListDtoResponseEntity(List<RequisitesDto> objectDtoList) {
        return objectDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDtoList, HttpStatus.OK);
    }

    /**
     * Формирует необходимый ответ в зависимости от содержания objectDto
     */
    private ResponseEntity<RequisitesDto> getDtoResponseEntity(RequisitesDto objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }
}
