package ru.geekbrains.domodel.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
import ru.geekbrains.domodel.entities.constants.Roles;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.MeterService;

import java.security.Principal;
import java.util.List;

/**
 * Контроллер счетчиков показаний
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/meters")
@Secured({Roles.ROLE_ADMIN, Roles.ROLE_USER})
public class MeterController {

    // Сервис счетчиков
    private final MeterService meterService;

    // Сервис уккаунтов
    private final AccountService accountService;

    @GetMapping("")
    public String getMetersPage(Model model, Principal principal) {
        List<Account> accounts = accountService.getAccountsByUserUserame(principal.getName());
        for (Account account : accounts) {
            //TODO: исправить логику
            model.addAttribute("account", account);
            model.addAttribute("meters", account.getMeters());
            Meter meter = account.getMeters().stream().findFirst().get();
            model.addAttribute("meterDatas", meter.getMeterDatas());
        }
        return "meters/meters";
    }

    @PostMapping("/submit")
    public String submitData(MeterData meterData) {
        meterService.submitMeterData(meterData);
        return "redirect:/meters";
    }

    @GetMapping("/add")
    public String getAddPage(Model model, Principal principal) {
        model.addAttribute("accounts", accountService.getAccountsByUserUserame(principal.getName()));
        return "meters/add";
    }

    @PostMapping("/add")
    public String addMeter(Meter meter) {
        meterService.save(meter);
        return "redirect:/meters";
    }
}
