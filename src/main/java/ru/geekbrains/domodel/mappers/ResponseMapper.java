package ru.geekbrains.domodel.mappers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.geekbrains.domodel.dto.*;

import java.util.List;

/**
 * Класс для маппинга Dto объектов в Response. Формирует необходимый ответ в зависимости от содержания objectDto.
 */
public final class ResponseMapper {

    public static ResponseEntity<Boolean> getBooleanResponse(Boolean objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }

    public static ResponseEntity<AccountDto> getDtoResponse(AccountDto objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }

    public static ResponseEntity<AppealDto> getDtoResponse(AppealDto objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }

    public static ResponseEntity<DocumentDto> getDtoResponse(DocumentDto objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }

    public static ResponseEntity<InformationDto> getDtoResponse(InformationDto objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }

    public static ResponseEntity<RequisitesDto> getDtoResponse(RequisitesDto objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }

    public static ResponseEntity<UserDto> getDtoResponse(UserDto objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }

    public static ResponseEntity<VoteDto> getDtoResponse(VoteDto objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }

    public static ResponseEntity<List<AccountDto>> getListAccountDtoResponse(List<AccountDto> objectDtoList) {
        return objectDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDtoList, HttpStatus.OK);
    }

    public static ResponseEntity<List<AppealDto>> getListAppealDtoResponse(List<AppealDto> objectDtoList) {
        return objectDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDtoList, HttpStatus.OK);
    }

    public static ResponseEntity<List<DocumentDto>> getListDocumentDtoResponse(List<DocumentDto> documentDtoList) {
        return documentDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(documentDtoList, HttpStatus.OK);
    }

    public static ResponseEntity<List<InformationDto>> getListInformationDtoResponse(
            List<InformationDto> informationDtoList) {
        return informationDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(informationDtoList, HttpStatus.OK);
    }

    public static ResponseEntity<List<UserDto>> getListUserDtoResponse(List<UserDto> objectDtoList) {
        return objectDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDtoList, HttpStatus.OK);
    }

    public static ResponseEntity<List<VoteDto>> getListVoteDtoResponse(List<VoteDto> objectDtoList) {
        return objectDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDtoList, HttpStatus.OK);
    }
}
