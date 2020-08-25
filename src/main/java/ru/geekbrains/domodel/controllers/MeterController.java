package ru.geekbrains.domodel.controllers;

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
import ru.geekbrains.domodel.services.api.TariffService;

import java.security.Principal;
import java.util.ArrayList;
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

    //
    private final TariffService tariffService;

    @GetMapping("")
    public String getMetersPage(Model model, Principal principal) {
        List<Account> accounts = accountService.getAccountsByUserUserame(principal.getName());
        for (Account account : accounts) {
            //TODO: исправить логику
            model.addAttribute("account", account);
            model.addAttribute("meters", account.getMeters());
            List<Meter> meterList = account.getMeters();
            // TODO написать человеческий поиск всех показаний всех счетчиков аккаунта за один метод
            List<MeterData> meterDataList = new ArrayList<>();
            for (Meter meter : meterList) {
                meterDataList.addAll(meterService.getAllMeterDataByMeter(meter));
            }
            model.addAttribute("meterDatas", meterDataList);
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
        model.addAttribute("tariffs", tariffService.getAllTariffs());
        return "meters/add";
    }

    @PostMapping("/add")
    public String addMeter(Meter meter) {
        meterService.save(meter);
        return "redirect:/meters";
    }
}
