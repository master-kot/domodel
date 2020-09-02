package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
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
@CrossOrigin
@RestController
@RequestMapping("/api/v1/meters")
@Secured({Roles.ROLE_ADMIN, Roles.ROLE_USER})
@RequiredArgsConstructor
public class MeterController {

    // Список сервисов
    private final MeterService meterService;
    private final AccountService accountService;
    private final TariffService tariffService;
    private final UserService userService;

    @GetMapping("")
    public String getMetersPage(Model model, Principal principal) {
        if (principal != null) {
            UserDto user = userService.getUserByUsername(principal.getName());
            model.addAttribute("username", user.getPhone());
            model.addAttribute("user", user);
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
        model.addAttribute("accounts", accountService.getAccountsByUserUsername(principal.getName()));
        model.addAttribute("tariffs", tariffService.getAllTariffs());
        return "meters/meters_add";
    }

    @PostMapping("/add")
    public String addMeter(Meter meter) {
        meterService.save(meter);
        return "redirect:/meters";
    }
}
