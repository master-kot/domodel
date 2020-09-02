package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.MeterDataDto;
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

    @Secured({Roles.ROLE_ADMIN})
    @PutMapping("")
    public MeterDto updateMeter(@RequestBody MeterDto meterDto) {
        return meterService.save(meterDto);
    }

    @GetMapping("/{id}")
    public MeterDto readMeterById(@PathVariable Long id) {
        return meterService.getMeter(id);
    }

    @GetMapping("/{id}/data")
    public List<MeterDataDto> readAllMeterDataByMeterId(@PathVariable Long id) {
        return meterService.getAllMeterDataByMeterId(id);
    }
}
