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
import ru.geekbrains.domodel.entities.UserRepresentation;
import ru.geekbrains.domodel.services.api.UserService;

import javax.validation.Valid;
import java.security.Principal;

import static ru.geekbrains.domodel.entities.constants.Messages.PASSWORD_MISMATCH;

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
        model.addAttribute("userData", new UserRepresentation());
        return "pages/profile";
    }

    /**
     * Перехват запроса на изменение профиля пользователя
     */
    @PostMapping("/change")
    public String changeUserProfile(@Valid @ModelAttribute("userData") UserRepresentation userData,
                                    BindingResult bindingResult,
                                    Model model,
                                    Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        if (bindingResult.hasErrors()) {
            return "pages/profile";
        }

        if (!userData.getPassword().equals(userData.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "", PASSWORD_MISMATCH);
            return "pages/profile";
        }

        userService.updateUser(userData, user);
        return "redirect:/profile";
    }
}
