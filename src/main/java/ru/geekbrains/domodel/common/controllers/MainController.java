package ru.geekbrains.domodel.common.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.domodel.users.entities.UserRepresentation;
import ru.geekbrains.domodel.users.services.api.UserService;

/**
 * Главный контроллер web-приложения
 */
@Controller
public class MainController {

    // Сервис пользователей
    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Перехват запроса корневой страницы
     */
    @GetMapping("")
    public String homePage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("request", new UserRepresentation());
        return "index";
    }

}
