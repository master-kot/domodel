package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.UserRepresentation;
import ru.geekbrains.domodel.services.api.UserService;

import javax.validation.Valid;

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
        if (error != null && error.equals("authenticationFailed")) {
            model.addAttribute("error",
                    "Ошибка авторизации: пользователь не существует, либо пароль не верный");
        }
        model.addAttribute("request", new UserRepresentation());
        return "index";
    }

    /**
     * Создать нового пользователя
     */
    @PostMapping("/createUser")
    public String createNewUser(@ModelAttribute @Valid UserRepresentation request,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", null);
            return "index";
        }

        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "", "Пароли не совпадают");
            model.addAttribute("user", null);
            model.addAttribute("error",
                    "Пользователь не создан. Введенные пароли не совпадают");
            return "index";
        }

        User user = userService.createNewUser(request);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", null);
            model.addAttribute("error",
                    "В системе уже зарегистрирован пользователь с именем " + request.getLogin());
        }
        return "index";
    }

}
