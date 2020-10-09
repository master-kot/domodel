package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.Api;
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
@Api("Доступ к разделу только для зарегистрированных пользователей и Администратора")
@RequestMapping("/api/v1/meters")
@RequiredArgsConstructor
public class MeterController {

    // Сервис счетчиков
    private final MeterService meterService;

    @ApiOperation(value = "Выводит список всех счетчиков. Только для администратора")
    @Secured(Roles.ROLE_ADMIN)
    @GetMapping("/all")
    public List<MeterDto> readAllMeters(Authentication authentication) {
        //TODO возвращаем ResponseEntity<List<MeterDto>> используя статические методы класса ResponseMapper
        return meterService.getAllMeters(authentication);
    }

    @ApiOperation(value = "Выводит список счетчиков по имени пользователя")
    @GetMapping("")
    public List<AccountMetersDto> readMetersByUserUsername(Authentication authentication) {
        //TODO возвращаем ResponseEntity<List<MeterDto>> используя статические методы класса ResponseMapper
        return meterService.getMetersByUserUsername(authentication);
    }

    @ApiOperation(value = "Выводит информацию о счетчике по его индексу")
    @GetMapping("/{id}")
    public MeterDto readMeterById(@PathVariable Long id) {
        //TODO возвращаем ResponseEntity<MeterDto> используя статические методы класса ResponseMapper
        //TODO предусмотреть в сервисе защиту от получения данных по счетчику не принадлежащему пользователю
        return meterService.getMeterById(id);
    }

    @ApiOperation(value = "Обновляет информацию о счетчике. Только для администратора")
    @Secured({Roles.ROLE_ADMIN})
    @PutMapping("/{id}")
    public MeterDto updateMeter(@PathVariable Long id, @RequestBody MeterDto meterDto) {
        //TODO возвращаем ResponseEntity<MeterDto> используя статические методы класса ResponseMapper
        //TODO предусмотреть в сервисе защиту от получения данных по счетчику не принадлежащему пользователю
        return meterService.saveOrUpdate(id, meterDto);
    }

    @ApiOperation(value = "Создает новый счетчик. Только для администратора")
    @Secured({Roles.ROLE_ADMIN})
    @PostMapping("")
    public ResponseEntity<MeterDto> createMeter(@RequestBody MeterDto meterDto) {
        MeterDto m = meterService.saveOrUpdate(null, meterDto);
        if (m == null) {
            return ResponseEntity.noContent().build();
        }
        //TODO возвращаем ResponseEntity<...> используя статические методы класса ResponseMapper
        return ResponseEntity.ok(m);
    }

    @ApiOperation(value = "Удаляет счетчик по его индексу")
    @Secured(Roles.ROLE_ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeter(@PathVariable Long id) {
        Integer result = meterService.deleteMeterById(id);
        //TODO возвращаем ResponseEntity<...> используя статические методы класса ResponseMapper
        return result > 0 ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Выводит список показаний для всех счетчиков. Только для администратора")
    @PostMapping("/all/data")
    public ResponseEntity<?> createMeterDatas(@RequestBody List<SubmitDataDto> submitData,
                                              Authentication authentication) {
        List<MeterDataDto> result = meterService.submitAllMeterData(submitData, authentication);
        //TODO возвращаем ResponseEntity<...> используя статические методы класса ResponseMapper
        return  !result.isEmpty() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    //
    @ApiOperation(value = "Выводит информацию о всех показаниях счетчика по индексу счетчика")
    @GetMapping("/{id}/data")
    public List<MeterDataDto> readAllMeterDataByMeterId(@PathVariable Long id) {
        //TODO предусмотреть в сервисе защиту от получения данных по счетчику, не принадлежащему пользователю
        //TODO возвращаем ResponseEntity<List<...>> используя статические методы класса ResponseMapper
        return meterService.getAllMeterDataByMeterId(id);
    }

    @ApiOperation(value = "Создает новые показания счетчика по индексу счетчика")
    @PostMapping("/{id}/data")
    public ResponseEntity<?> createMeterDataByMeterId(@PathVariable Long id,
                                                      @RequestParam Double submitData,
                                                      Authentication authentication) {
        //TODO предусмотреть в сервисе что показания по любому счетчику может подавать администратор
        MeterDataDto result = meterService.submitMeterData(id, submitData, authentication);
        //TODO возвращаем ResponseEntity<List<...>> используя статические методы класса ResponseMapper
        return  result != null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Удаляет показания счетчика по индексу показаний. Только для администратора")
    @Secured({Roles.ROLE_ADMIN})
    @DeleteMapping("/data/{dataId}")
    public ResponseEntity<?> deleteMeterDataById(@PathVariable Long dataId) {
        Integer result = meterService.deleteMeterDataById(dataId);
        //TODO возвращаем ResponseEntity<...> используя статические методы класса ResponseMapper
        return result > 0 ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Выводит список типов счетчика. Только для администратора")
    @Secured({Roles.ROLE_ADMIN})
    @GetMapping("/types")
    public List<MeterTypeDto> readMeterTypes() {
        //TODO возвращаем ResponseEntity<List<...>> используя статические методы класса ResponseMapper
        return meterService.getMeterTypes();
    }

    @ApiOperation(value = "Выводит список тарифов. Только для администратора")
    @Secured({Roles.ROLE_ADMIN})
    @GetMapping("/tariffs")
    public List<TariffDto> readMeterTariffs() {
        //TODO возвращаем ResponseEntity<List<...>> используя статические методы класса ResponseMapper
        return meterService.getMeterTariffs();
    }
}
