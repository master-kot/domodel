package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Контроллер информационных страниц
 */
@Controller
@RequestMapping("/information")
@RequiredArgsConstructor
public class InformationController {

    /**
     * Перехват запроса страницы о компании (ВРЕМЕННОЕ РЕШЕНИЕ)
     */
    @GetMapping("/about")
    public String getAboutPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "information/about";
    }

    /**
     * Перехват запроса страницы контакты (ВРЕМЕННОЕ РЕШЕНИЕ)
     */
    @GetMapping("/contacts")
    public String getContactsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "information/contacts";
    }

    /**
     * Перехват запроса страницы документы (ВРЕМЕННОЕ РЕШЕНИЕ)
     */
    @GetMapping("/documents")
    public String getDocumentsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "information/documents";
    }
}
