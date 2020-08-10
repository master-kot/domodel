package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.BillService;

import java.security.Principal;

/**
 * Контроллер счетов (платежных документов)
 */
@Controller
@RequestMapping("/bills")
public class BillController {

    // Сервис счетов
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    /**
     * Перехват запроса списка новостей
     */
    @GetMapping("")
    public String getBillsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("bills", billService.getAllBills());
        return "bills";
    }
}
