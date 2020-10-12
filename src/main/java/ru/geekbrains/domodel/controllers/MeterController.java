package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.*;
import ru.geekbrains.domodel.entities.constants.Roles;
import ru.geekbrains.domodel.mappers.ResponseMapper;
import ru.geekbrains.domodel.services.api.MeterService;

import java.util.List;

/**
 * Контроллер счетчиков показаний
 */
@CrossOrigin
@RestController
@Secured({Roles.ROLE_ADMIN, Roles.ROLE_USER})
@Api("Доступ к разделу только для зарегистрированных пользователей и Администратора")
@RequestMapping("/api/v1/meters")
@RequiredArgsConstructor
public class MeterController {

    // Сервис счетчиков
    private final MeterService meterService;

    @ApiOperation(value = "Выводит список всех счетчиков. Только для администратора")
    @Secured(Roles.ROLE_ADMIN)
    @GetMapping("/all")
    public ResponseEntity<List<MeterDto>> readAllMeters(Authentication authentication) {
        return ResponseMapper.getListMeterDtoResponse(meterService.getAllMeters(authentication));
    }

    @ApiOperation(value = "Выводит список счетчиков по имени пользователя")
    @GetMapping("")
    public ResponseEntity<List<AccountMetersDto>> readMetersByUserUsername(Authentication authentication) {
        return ResponseMapper.getListAccountMeterDtoResponse(meterService.getMetersByUserUsername(authentication));
    }

    @ApiOperation(value = "Выводит информацию о счетчике по его индексу")
    @GetMapping("/{id}")
    public ResponseEntity<MeterDto> readMeterById(@PathVariable Long id) {
        //TODO предусмотреть в сервисе защиту от получения данных по счетчику не принадлежащему пользователю
        return ResponseMapper.getDtoResponse(meterService.getMeterById(id));
    }

    @ApiOperation(value = "Обновляет информацию о счетчике. Только для администратора")
    @Secured({Roles.ROLE_ADMIN})
    @PutMapping("/{id}")
    public ResponseEntity<MeterDto> updateMeter(@PathVariable Long id, @RequestBody MeterDto meterDto) {
        //TODO возвращаем ResponseEntity<MeterDto> используя статические методы класса ResponseMapper
        return ResponseMapper.getDtoResponse(meterService.saveOrUpdate(id, meterDto));
    }

    @ApiOperation(value = "Создает новый счетчик. Только для администратора")
    @Secured({Roles.ROLE_ADMIN})
    @PostMapping("")
    public ResponseEntity<MeterDto> createMeter(@RequestBody MeterDto meterDto) {
        MeterDto m = meterService.saveOrUpdate(null, meterDto);
        return m == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(m, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаляет счетчик по его индексу")
    @Secured(Roles.ROLE_ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeter(@PathVariable Long id) {
        Integer result = meterService.deleteMeterById(id);
        return result > 0 ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Выводит список показаний для всех счетчиков. Только для администратора")
    @Secured({Roles.ROLE_ADMIN})
    @PostMapping("/all/data")
    public ResponseEntity<?> createMeterData(@RequestBody List<SubmitDataDto> submitData, Authentication authentication) {
        List<MeterDataDto> result = meterService.submitAllMeterData(submitData, authentication);
        return  result.isEmpty() ? ResponseEntity.badRequest().build() : ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Выводит информацию о всех показаниях счетчика по индексу счетчика")
    @GetMapping("/{id}/data")
    public ResponseEntity<List<MeterDataDto>> readAllMeterDataByMeterId(@PathVariable Long id) {
        //TODO предусмотреть в сервисе защиту от получения данных по счетчику, не принадлежащему пользователю
        return ResponseMapper.getListMeterDataDtoResponse(meterService.getAllMeterDataByMeterId(id));
    }

    @ApiOperation(value = "Создает новые показания счетчика по индексу счетчика")
    @PostMapping("/{id}/data")
    public ResponseEntity<?> createMeterDataByMeterId(@PathVariable Long id, @RequestParam Double submitData, Authentication authentication) {
        //TODO предусмотреть в сервисе что показания по любому счетчику может подавать администратор
        MeterDataDto result = meterService.submitMeterData(id, submitData, authentication);
        return  result != null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Удаляет показания счетчика по индексу показаний. Только для администратора")
    @Secured({Roles.ROLE_ADMIN})
    @DeleteMapping("/data/{dataId}")
    public ResponseEntity<?> deleteMeterDataById(@PathVariable Long dataId) {
        Integer result = meterService.deleteMeterDataById(dataId);
        return result > 0 ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Выводит список типов счетчика. Только для администратора")
    @Secured({Roles.ROLE_ADMIN})
    @GetMapping("/types")
    public ResponseEntity<List<MeterTypeDto>> readMeterTypes() {
        return ResponseMapper.getListMeterTypeDtoResponse(meterService.getMeterTypes());
    }

    @ApiOperation(value = "Выводит список тарифов. Только для администратора")
    @Secured({Roles.ROLE_ADMIN})
    @GetMapping("/tariffs")
    public ResponseEntity<List<TariffDto>> readMeterTariffs() {
        return ResponseMapper.getListTariffDtoResponse(meterService.getMeterTariffs());
    }
}
