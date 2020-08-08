package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.UserService;

/**
 * Контроллер модуля управления пользователями
 */
@Controller
@RequestMapping("/users")
public class UserManagementController {

    // Сервис пользователей
    private final UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Перехват запроса списка всех пользователей
     */
    @GetMapping("")
    public String getAllUsersPage(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
