package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.AccountService;

import java.security.Principal;

/**
 * Контроллер модуля новостей
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

    // Сервис реквизитов компании
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Перехват запроса списка реквизитов компании
     */
    @GetMapping("")
    public String getAccountsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accounts";
    }
}
