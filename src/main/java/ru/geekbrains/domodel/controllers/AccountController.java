package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.services.api.AccountService;

/**
 * Контроллер лицевых счетов
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Log4j2
public class AccountController {

    // Сервис лицевых счетов
    private final AccountService accountService;
}
