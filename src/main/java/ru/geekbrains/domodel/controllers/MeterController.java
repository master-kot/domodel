package ru.geekbrains.domodel.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
import ru.geekbrains.domodel.entities.constants.Roles;
import ru.geekbrains.domodel.services.api.MeterService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Контроллер счетчиков показаний
 */
@Controller
@RequestMapping("/meters")
@AllArgsConstructor
@Secured({Roles.ROLE_ADMIN, Roles.ROLE_USER})
public class MeterController {

    private final MeterService meterService;

    @GetMapping("")
    public String meter(Model model, Principal principal) {
        Optional<Meter> meter = meterService.getMeter(principal.getName());
        model.addAttribute("meters", meter);
        model.addAttribute("meterDatas", meterService.getAllMeterData(meter.get()).orElse(new ArrayList<>()));
        return "meters";
    }

    @PostMapping("/submit")
    public String submitData(MeterData meterData) {
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
