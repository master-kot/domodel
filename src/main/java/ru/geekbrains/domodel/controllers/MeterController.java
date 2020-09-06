package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
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

    // Список необходимых сервисов
    private final MeterService meterService;
    private final AccountService accountService;
    private final TariffService tariffService;
    private final UserService userService;

    @ApiOperation(value = "Выводит список счетчиков данных пользователя")
    @GetMapping("")
    public String getMetersPage(Model model, Principal principal) {
        if (principal != null) {
            UserDto user = userService.getByUsername(principal.getName());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("user", user);
        }
        model.addAttribute("meterData", new MeterData());
        return "meters/meters_user";
    }

    @ApiOperation(value = "Выводит информацию о счетчике по его индексу")
    @GetMapping("/{id}")
    public String getMetersArchivePage(@PathVariable String id, Model model) {
        Meter meter = meterService.getMeter(Long.valueOf(id));
        model.addAttribute("meter", meter);
        model.addAttribute("account", meter.getAccount());

        model.addAttribute("meterDatas", meterService.getAllMeterDataByMeter(meter));
        return "meters/meters_archive";
    }

    @ApiOperation(value = "Принимает данные о показаниях счетчика")
    @PostMapping("/submit")
    public String submitData(MeterData meterData) {
        if (meterData.getValue() != null && meterData.getValue() != 0) {
            meterService.submitMeterData(meterData);
        }
        return "redirect:/meters/";
    }

    @ApiOperation(value = "Отдает данные для создания нового счетчика")
    @GetMapping("/add")
    public String getAddPage(Model model, Principal principal) {
        model.addAttribute("accounts", accountService.getAllAccountsByUserUsername(principal.getName()));
        model.addAttribute("tariffs", tariffService.getAllTariffs());
        return "meters/meters_add";
    }

    @ApiOperation(value = "Принимает данные для создания нового счетчика")
    @PostMapping("/add")
    public String addMeter(Meter meter) {
        meterService.save(meter);
        return "redirect:/meters";
    }
}
