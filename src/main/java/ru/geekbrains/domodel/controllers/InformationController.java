package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.InformationService;

import java.security.Principal;

/**
 * Контроллер информационных страниц
 */
@Controller
@RequestMapping("/information")
@RequiredArgsConstructor
public class InformationController {

    // Сервис информации
    private final InformationService informationService;

    /**
     * Перехват запроса страницы информации о компании
     */
    @GetMapping("/about")
    public String getAboutPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "information/about";
    }

    /**
     * Перехват запроса страницы контакты
     */
    @GetMapping("/contacts")
    public String getContactsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "information/contacts";
    }

    /**
     * Перехват запроса страницы документы
     */
    @GetMapping("/documents")
    public String getDocumentsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "information/documents";
    }

    /**
     * Перехват запроса страницы реквизитов
     */
    @GetMapping("/requisites")
    public String getRequisitesPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "information/requisites";
    }
}
