package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.UserService;

import java.security.Principal;

/**
 * Контроллер модуля профиля пользователя
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    // Сервис пользователей
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Перехват запроса профиля пользователя
     */
    @GetMapping("")
    public String getAllUsersPage(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        return "profile";
    }
}
