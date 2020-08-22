package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.RequisitesService;
import ru.geekbrains.domodel.services.api.UserService;

import java.security.Principal;

/**
 * Контроллер модуля управления сайтом
 */
@Controller
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementController {

    // Сервис пользователей
    private final UserService userService;

    // Сервис реквизитов
    private final RequisitesService requisitesService;

    /**
     * Перехват запроса списка всех пользователей
     */
    @GetMapping("/users")
    public String getAllUsersPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    /**
     * Перехват запроса удаления пользователя
     */
    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/management/users";
    }

    /**
     * Перехват запроса списка реквизитов компании
     */
    @GetMapping("/requisites")
    public String getRequisitesPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("requisites", requisitesService.getAllRequisites());
        return "requisites";
    }
}
