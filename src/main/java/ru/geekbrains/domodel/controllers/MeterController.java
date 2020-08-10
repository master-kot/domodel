package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.MeterService;

import java.security.Principal;

/**
 * Контроллер счетчиков показаний
 */
@Controller
@RequestMapping("/meters")
public class MeterController {

    // Сервис счетчиков показаний
    private final MeterService meterService;

    @Autowired
    public MeterController(MeterService meterService) {
        this.meterService = meterService;
    }

    /**
     * Перехват запроса списка новостей
     */
    @GetMapping("")
    public String getMetersPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("meters", meterService.getAllMeters());
        return "meters";
    }
}
