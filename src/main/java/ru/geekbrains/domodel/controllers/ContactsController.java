package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.ContactsService;

import java.security.Principal;

/**
 * Контроллер контактной информации компании
 */
@Controller
@RequestMapping("/contacts")
public class ContactsController {

    // Сервис контактной информации компании
    private final ContactsService contactsService;

    @Autowired
    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    /**
     * Перехват запроса страницы контактной информации
     */
    @GetMapping("")
    public String getContactsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "contacts";
    }
}
