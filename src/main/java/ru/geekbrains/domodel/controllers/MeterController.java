package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.MeterDto;
import ru.geekbrains.domodel.entities.constants.Roles;
import ru.geekbrains.domodel.services.api.MeterService;

import java.util.List;


/**
 * Контроллер счетчиков показаний
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/meters")
@Secured({Roles.ROLE_ADMIN, Roles.ROLE_USER})
@RequiredArgsConstructor
public class MeterController {

    private final MeterService meterService;

    @Secured(Roles.ROLE_ADMIN)
    @GetMapping("")
    public List<MeterDto> readAllMeters() {
        return meterService.getAllMeters();
    }

    @GetMapping("/{id}")
    public MeterDto readMeterById(@PathVariable String id) {
        return meterService.getMeter(Long.valueOf(id));
    }

    @Secured(Roles.ROLE_ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeter(@PathVariable Long id) {
        meterService.deleteMeter(id);
        return ResponseEntity.noContent().build();
    }

    @Secured({Roles.ROLE_ADMIN})
    @PostMapping("")
    public MeterDto createMeter(@RequestBody MeterDto meterDto) {
        return meterService.save(meterDto);
    }
}
