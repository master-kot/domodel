package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.services.api.UserService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Контроллер модуля профиля пользователя
 */
@Controller
@RequestMapping("/profile")
public class UserProfileController {

    // Сервис пользователей
    private final UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Перехват запроса на чтение профиля пользователя
     */
    @GetMapping("")
    public String getUserProfilePage(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        return "profile";
    }

    /**
     * Перехват запроса на изменение профиля пользователя
     */
    @PostMapping("/profile/change")
    public String changeUserProfile(@Valid @ModelAttribute("user") User user,
                                    BindingResult bindingResult,
                                    Model model,
                                    Principal principal) {
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        model.addAttribute("user",
                model.addAttribute("user", userService.updateUser(user, principal.getName())));
        return "profile";
    }
}
