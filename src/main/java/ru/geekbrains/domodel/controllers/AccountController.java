package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.AccountService;

import java.security.Principal;

/**
 * Контроллер лицевых счетов
 */
@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Log4j2
public class AccountController {

    // Сервис лицевых счетов
    private final AccountService accountService;

    /**
     * Перехват запроса списка лицевых счетов
     */
    @GetMapping("")
    public String getAccountsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "profile/accounts";
    }
}
