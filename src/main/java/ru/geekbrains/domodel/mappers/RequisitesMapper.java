package ru.geekbrains.domodel.mappers;

import org.springframework.stereotype.Component;
import ru.geekbrains.domodel.dto.RequisitesDto;
import ru.geekbrains.domodel.entities.Requisites;

@Component
public class RequisitesMapper {

    public static RequisitesDto requisitesToRequisitesDto(Requisites entity) {
        RequisitesDto dto = new RequisitesDto();
        dto.setId(entity.getId());
        dto.setCompanyName(entity.getCompanyName());
        dto.setCompanyAddress(entity.getCompanyAddress());
        dto.setInn(entity.getInn());
        dto.setKpp(entity.getKpp());
        dto.setOgrn(entity.getOgrn());
        dto.setBankAccount(entity.getBankAccount());
        dto.setBankName(entity.getBankName());
        dto.setBik(entity.getBik());
        dto.setCorrespondentAccount(entity.getCorrespondentAccount());
        return dto;
    }

    public static Requisites requisitesDtoToRequisites(RequisitesDto dto) {
        Requisites entity = new Requisites();
        entity.setId(dto.getId());
        entity.setCompanyName(dto.getCompanyName());
        entity.setCompanyAddress(dto.getCompanyAddress());
        entity.setInn(dto.getInn());
        entity.setKpp(dto.getKpp());
        entity.setOgrn(dto.getOgrn());
        entity.setBankAccount(dto.getBankAccount());
        entity.setBankName(dto.getBankName());
        entity.setBik(dto.getBik());
        entity.setCorrespondentAccount(dto.getCorrespondentAccount());
        return entity;
    }
}
