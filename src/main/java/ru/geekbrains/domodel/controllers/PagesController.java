package ru.geekbrains.domodel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Временый контроллер для различных страниц
 */
@Controller
@RequestMapping("/pages")
public class PagesController {

    /**
     * Перехват запроса страницы логина (ВРЕМЕННОЕ РЕШЕНИЕ)
     */
    @GetMapping("login")
    public String getLoginPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "pages/login";
    }

    /**
     * Перехват запроса страницы о компании (ВРЕМЕННОЕ РЕШЕНИЕ)
     */
    @GetMapping("/about")
    public String getAboutPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "pages/about";
    }

    /**
     * Перехват запроса страницы контакты (ВРЕМЕННОЕ РЕШЕНИЕ)
     */
    @GetMapping("/contacts")
    public String getContactsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "pages/contacts";
    }

    /**
     * Перехват запроса страницы контакты (ВРЕМЕННОЕ РЕШЕНИЕ)
     */
    @GetMapping("/personal")
    public String getPersonalPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "pages/personal";
    }
}
