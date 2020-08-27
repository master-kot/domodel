package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
import ru.geekbrains.domodel.entities.constants.Roles;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.MeterService;
import ru.geekbrains.domodel.services.api.TariffService;

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

    // Сервис лицевых счетов
    private final AccountService accountService;

    // Сервис тарифов
    private final TariffService tariffService;

    @GetMapping("")
    public String getMetersPage(Model model, Principal principal) {
        List<Account> accounts = accountService.getAccountsByUserUserame(principal.getName());
        model.addAttribute("accounts", accounts);
        model.addAttribute("meterData", new MeterData());
        return "meters/meters_user";
    }

    @GetMapping("/{id}")
    public String meterPage(@PathVariable String id, Model model) {
        Meter meter = meterService.getMeter(Long.valueOf(id));
        model.addAttribute("meter", meter);
        model.addAttribute("account", meter.getAccount());

        model.addAttribute("meterDatas", meterService.getAllMeterDataByMeter(meter));
        return "meters/meterPage";
    }

    @PostMapping("/submit")
    public String submitData(MeterData meterData) {
        meterService.submitMeterData(meterData);
        return "redirect:/meters/";
    }

    @GetMapping("/add")
    public String getAddPage(Model model, Principal principal) {
        model.addAttribute("accounts", accountService.getAccountsByUserUserame(principal.getName()));
        model.addAttribute("tariffs", tariffService.getAllTariffs());
        return "meters/meters_add";
    }

    @PostMapping("/add")
    public String addMeter(Meter meter) {
        meterService.save(meter);
        return "redirect:/meters";
    }
}
