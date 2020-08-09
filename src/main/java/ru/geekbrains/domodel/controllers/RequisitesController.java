package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.RequisitesService;

import java.security.Principal;

/**
 * Контроллер реквизитов компании
 */
@Controller
@RequestMapping("/requisites")
public class RequisitesController {

    // Сервис реквизитов компании
    private final RequisitesService requisitesService;

    @Autowired
    public RequisitesController(RequisitesService requisitesService) {
        this.requisitesService = requisitesService;
    }

    /**
     * Перехват запроса списка реквизитов компании
     */
    @GetMapping("")
    public String getRequisitesPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("requisites", requisitesService.getAllRequisites());
        return "requisites";
    }
}
