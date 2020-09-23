package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import ru.geekbrains.domodel.dto.BillDto;
import ru.geekbrains.domodel.entities.Bill;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * Маппер, преобразовывающий классы Bill и BillDto друг в друга
 */
@Mapper(componentModel = "spring", uses= {AccountMapper.class, RequisitesMapper.class})
public abstract class BillMapper {

    public BillDto billToBillDto(Bill entity) {
        BillDto dto = new BillDto();
        dto.setId(entity.getId());
        dto.setCreationDate(entity.getCreationDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        dto.setTarget(entity.getTarget());
        dto.setTotal(entity.getTotal());
        dto.setType(entity.getType().name());
        dto.setPaymentStatus(entity.isPaymentStatus());
        dto.setAccount(entity.getAccount().getId());
        dto.setRequisites(entity.getRequisites().getId());
        dto.setCalculations(entity.getCalculations().stream()
                .map(CalculationMapper::calculationToCalculationDto).collect(Collectors.toList()));
        return dto;
    }
}
