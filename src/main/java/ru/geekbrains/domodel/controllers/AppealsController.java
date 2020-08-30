package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.AppealsService;

import java.security.Principal;

/**
 * Контроллер обращений
 */
@Controller
@RequestMapping("/appeals")
@RequiredArgsConstructor
public class AppealsController {

    // Сервис обращений
    private final AppealsService appealsService;

    /**
     * Перехват запроса главной страницы обращений
     */
    @GetMapping("")
    public String getAppealsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "appeals/appeals_user";
    }
}
