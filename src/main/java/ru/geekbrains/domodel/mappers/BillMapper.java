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
@Mapper(componentModel = "spring", uses = {CalculationMapper.class})
public interface BillMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="creationDate", source = "entity.creationDate", dateFormat = "dd-MM-yyyy"),
            @Mapping(target="target", source = "entity.target"),
            @Mapping(target="total", source = "entity.total"),
            @Mapping(target="type", ignore = true), //source = "entity.type.name"),
            @Mapping(target="paymentStatus", source = "entity.paymentStatus"),
            @Mapping(target="account", source = "entity.account.id"),
            @Mapping(target="requisites", source = "entity.requisites.id"),
            @Mapping(target="calculations", source = "entity.calculations")
    })
    BillDto billToBillDto(Bill entity);

    List<BillDto> billToBillDto(List<Bill> entities);
}
