package ru.geekbrains.domodel.controllers;

import lombok.AllArgsConstructor;
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

/**
 * Контроллер счетчиков показаний
 */
@Controller
@AllArgsConstructor
@RequestMapping("/meters")
@Secured({Roles.ROLE_ADMIN, Roles.ROLE_USER})
public class MeterController {

    private final MeterService meterService;
    private final AccountService accountService;

    @GetMapping("")
    public String meter(Model model, Principal principal) {
        Account account = accountService.getAccountByUserName(principal.getName());
        model.addAttribute("account", account);
        model.addAttribute("meters", account.getMeters());
        //TODO: исправить логику
        Meter meter = account.getMeters().stream().findFirst().get();
        model.addAttribute("meterDatas", meter.getMeterDatas());
        return "meters";
    }

    @PostMapping("/submit")
    public String submitData(MeterData meterData) {
        meterService.submitData(meterData);
        return "redirect:/meters";
    }

    @GetMapping("/add")
    public String addPage() {
        return "meterAddPage";
    }

    @PostMapping("/add")
    public String addOne(Meter meter, Principal principal) {
        meterService.save(meter, principal.getName());
        return "redirect:/meters";
    }
}
