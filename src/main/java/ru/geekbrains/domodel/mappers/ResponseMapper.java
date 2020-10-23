package ru.geekbrains.domodel.mappers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.geekbrains.domodel.dto.*;

import java.util.List;

/**
 * Класс для маппинга Dto объектов в Response. Формирует необходимый ответ в зависимости от содержания objectDto.
 */
public final class ResponseMapper {

    public static ResponseEntity<MeterDto> getDtoResponse(MeterDto objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }

    public static ResponseEntity<List<MeterDto>> getListMeterDtoResponse(List<MeterDto> informationDtoList) {
        return informationDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(informationDtoList, HttpStatus.OK);
    }

    public static ResponseEntity<List<AccountMetersDto>> getListAccountMeterDtoResponse(List<AccountMetersDto> objectDtoList) {
        return objectDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDtoList, HttpStatus.OK);
    }

    public static ResponseEntity<List<MeterDataDto>> getListMeterDataDtoResponse(List<MeterDataDto> objectDtoList) {
        return objectDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDtoList, HttpStatus.OK);
    }

    public static ResponseEntity<List<MeterTypeDto>> getListMeterTypeDtoResponse(List<MeterTypeDto> objectDtoList) {
        return objectDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDtoList, HttpStatus.OK);
    }

    public static ResponseEntity<List<TariffDto>> getListTariffDtoResponse(List<TariffDto> objectDtoList) {
        return objectDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDtoList, HttpStatus.OK);
    }
}
