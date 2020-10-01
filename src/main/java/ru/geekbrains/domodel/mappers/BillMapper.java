package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.BillDto;
import ru.geekbrains.domodel.entities.Bill;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Bill и BillDto друг в друга
 */
@Mapper(componentModel = "spring", uses = {CalculationMapper.class, BillTypeMapper.class})
public interface BillMapper {

    @Mappings({
            @Mapping(target="creationDate", source = "entity.creationDate", dateFormat = "dd-MM-yyyy"),
            @Mapping(target="typeDescription", source = "entity.type"),
            @Mapping(target="accountId", source = "entity.account.id"),
            @Mapping(target="requisitesId", source = "entity.requisites.id")
    })
    BillDto billToBillDto(Bill entity);

    List<BillDto> billToBillDto(List<Bill> entities);
}
