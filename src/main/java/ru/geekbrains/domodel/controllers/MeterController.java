package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.constants.Roles;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.MeterService;
import ru.geekbrains.domodel.services.api.TariffService;
import ru.geekbrains.domodel.services.api.UserService;

import java.security.Principal;

/**
 * Контроллер счетчиков показаний
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/meters")
@Secured({Roles.ROLE_ADMIN, Roles.ROLE_USER})
public class MeterController {

    // Список сервисов
    private final MeterService meterService;
    private final AccountService accountService;
    private final TariffService tariffService;
    private final UserService userService;

    @GetMapping("")
    public String getMetersPage(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.getUserByUsername(principal.getName());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("accounts", user.getAccounts());
        }
        model.addAttribute("meterData", new MeterData());
        return "meters/meters_user";
    }

    @GetMapping("/{id}")
    public String getMetersArchivePage(@PathVariable String id, Model model) {
        Meter meter = meterService.getMeter(Long.valueOf(id));
        model.addAttribute("meter", meter);
        model.addAttribute("account", meter.getAccount());

        model.addAttribute("meterDatas", meterService.getAllMeterDataByMeter(meter));
        return "meters/meters_archive";
    }

    @PostMapping("/submit")
    public String submitData(MeterData meterData) {
        if (meterData.getValue() != null && meterData.getValue() != 0) {
            meterService.submitMeterData(meterData);
        }
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
