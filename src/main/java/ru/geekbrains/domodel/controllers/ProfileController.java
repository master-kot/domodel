package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.services.api.UserService;

import javax.validation.Valid;
import java.security.Principal;

import static ru.geekbrains.domodel.entities.constants.Messages.PASSWORD_MISMATCH;

/**
 * Контроллер модуля профиля пользователя
 */
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    // Адреса шаблонов страниц
    private static final String PROFILE_EDIT_FORM = "profile/profile_edit";
    private static final String PROFILE_USER_FORM = "profile/profile_user";

    // Адреса страниц для переадресации
    private static final String PROFILE_FORM_REDIRECT_URL = "redirect:/profile";

    // Список необходимых сервисов
    private final UserService userService;

    /**
     * Перехват запроса на чтение профиля пользователя
     */
    @GetMapping("")
    public String getProfilePage(Model model, Principal principal) {
        User user = null;
        if (principal != null) {
            model.addAttribute("username", principal.getName());
            user = userService.getUserByUsername(principal.getName());
        }
        model.addAttribute("user", user);
        return PROFILE_USER_FORM;
    }

    /**
     * Перехват запроса на изменение профиля пользователя
     */
    @PostMapping("/edit")
    public String changeUserProfile(@Valid @ModelAttribute("userData") UserDto userData,
                                    BindingResult bindingResult,
                                    Model model,
                                    Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        if (bindingResult.hasErrors()) {
            return PROFILE_EDIT_FORM;
        }
        if (!userData.getPassword().equals(userData.getPasswordConfirm())) {
            bindingResult.rejectValue("password", "", PASSWORD_MISMATCH);
            return PROFILE_EDIT_FORM;
        }
        userService.updateUser(userData, user);
        return PROFILE_FORM_REDIRECT_URL;
    }

    /**
     * Перехват запроса на изменение ФИО пользователя
     */
    @PostMapping("/edit/name")
    public String editUserName(@Valid @ModelAttribute("user") User user,
                                Principal principal) {
        if (user.getLastName() != null || user.getFirstName() != null || user.getPatronymic() != null) {
            userService.editUser(user, principal.getName());
        }
        return PROFILE_FORM_REDIRECT_URL;
    }
}
