package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.*;
import ru.geekbrains.domodel.entities.constants.Roles;
import ru.geekbrains.domodel.services.api.MeterService;

import java.util.List;

/**
 * Контроллер счетчиков показаний
 */
@CrossOrigin
@RestController
@Secured({Roles.ROLE_ADMIN, Roles.ROLE_USER})
@ApiOperation("Доступ к разделу только для зарегистрированных пользователей и Администратора")
@RequestMapping("/api/v1/meters")
@RequiredArgsConstructor
public class MeterController {

    private final MeterService meterService;

    @ApiOperation(value = "Выводит счетчики отсортированные по лицевому счету")
    @GetMapping("")
    public List<AccountMetersDto> readMetersUser(Authentication authentication) {
        return meterService.getMetersUser(authentication);
    }

    @ApiOperation(value = "Выводит список всех счетчиков")
    @Secured(Roles.ROLE_ADMIN)
    @GetMapping("/all")
    public List<MeterDto> readAllMeters(Authentication authentication) {
        return meterService.getAllMeters(authentication);
    }

    @ApiOperation(value = "Выводит информацию о счетчике по его индексу")
    @GetMapping("/{id}")
    public MeterDto readMeterById(@PathVariable Long id) {
        return meterService.getMeter(id);
    }

    @ApiOperation(value = "Создает новый счетчик")
    @Secured({Roles.ROLE_ADMIN})
    @PostMapping("")
    public ResponseEntity<MeterDto> createMeter(@RequestBody MeterDto meterDto) {
        MeterDto m = meterService.saveOrUpdate(null, meterDto);
        if (m == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(m);
    }

    @ApiOperation(value = "Удаляет счетчик по его индексу")
    @Secured(Roles.ROLE_ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeter(@PathVariable Long id) {
        Integer result = meterService.deleteMeterById(id);
        return result > 0 ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Обновляет информацию о счетчике")
    @Secured({Roles.ROLE_ADMIN})
    @PutMapping("/{id}")
    public MeterDto updateMeter(@PathVariable Long id, @RequestBody MeterDto meterDto) {
        return meterService.saveOrUpdate(id, meterDto);
    }

    @ApiOperation(value = "Выводит информацию о всех показаниях счетчика по индексу счетчика")
    @GetMapping("/{id}/data")
    public List<MeterDataDto> readAllMeterDataByMeterId(@PathVariable Long id) {
        return meterService.getAllMeterDataByMeterId(id);
    }

    @ApiOperation(value = "Создает новые показания счетчика по индексу счетчика")
    @PostMapping("/{id}/data")
    public ResponseEntity<?> createMeterDataByMeterId(@PathVariable Long id, @RequestParam Double submitData, Authentication authentication) {
        MeterDataDto result = meterService.submitMeterData(id, submitData, authentication);
        return  result != null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/all/data")
    public ResponseEntity<?> createMeterDatas(@RequestBody List<SubmitDataDto> submitData, Authentication authentication) {
        List<MeterDataDto> result = meterService.submitAllMeterData(submitData, authentication);
        return  !result.isEmpty() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Удаляет показания счетчика по индексу показаний")
    @Secured({Roles.ROLE_ADMIN})
    @DeleteMapping("/data/{dataId}")
    public ResponseEntity<?> deleteMeterDataById(@PathVariable Long dataId) {
        Integer result = meterService.deleteMeterDataById(dataId);
        return result > 0 ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Выводит список типов счетчика")
    @Secured({Roles.ROLE_ADMIN})
    @GetMapping("/types")
    public List<MeterTypeDto> readMeterTypes() {
        return meterService.getMeterTypes();
    }

    @ApiOperation(value = "Выводит список тарифов")
    @Secured({Roles.ROLE_ADMIN})
    @GetMapping("/tariffs")
    public List<TariffDto> readMeterTariffs() {
        return meterService.getMeterTariffs();
    }
}
