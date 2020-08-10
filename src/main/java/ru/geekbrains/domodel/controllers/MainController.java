package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.domodel.entities.UserRepresentation;
import ru.geekbrains.domodel.entities.constants.Messages;
import ru.geekbrains.domodel.services.api.UserService;

import javax.validation.Valid;
import java.security.Principal;

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
     * Перехват запроса главной страницы
     */
    @GetMapping("")
    public String getHomePage(@RequestParam(required = false) String error, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "index";
    }

    /**
     * Перехват запроса регистрации нового пользователя
     */
    @GetMapping("/register")
    public String getRegisterPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("user", new UserRepresentation());
        return "register";
    }

    /**
     * Перехват запроса создания нового пользователя
     */
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRepresentation user,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "", Messages.PASSWORD_MISMATCH);
            return "register";
        }

        if (userService.createUser(user) != null) {
            model.addAttribute("message",
                    String.format(Messages.USER_CREATED, user.getUsername()));
        } else {
            bindingResult.rejectValue("username", "",
                    String.format(Messages.USER_HAS_ALREADY_CREATED, user.getUsername()));
        }
        return "register";
    }
}
