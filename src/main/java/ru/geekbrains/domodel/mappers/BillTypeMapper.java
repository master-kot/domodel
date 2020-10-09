package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import ru.geekbrains.domodel.entities.BillType;

/**
 * Маппер, преобразовывающий классы BillType и String друг в друга
 */
@Mapper(componentModel = "spring")
public interface BillTypeMapper {

    default String billTypeToString(BillType entity) {
        return entity.getDescription();
    }

    default BillType stringToBillType(String string) {
        BillType entity;
        switch (string) {
            case "METERS" :
            case "Платеж по счетчикам":
                entity = BillType.METERS;
                break;
            case "MEMBERSHIP_FEE_FIXED":
            case "Членский взнос фиксированный":
                entity = BillType.MEMBERSHIP_FEE_FIXED;
                break;
            case "MEMBERSHIP_FEE_CALCULATED":
            case "Членский взнос рассчитанный":
                entity = BillType.MEMBERSHIP_FEE_CALCULATED;
                break;
            case "OTHER_FEE_FIXED":
            case "Прочий платеж фиксированный":
                entity = BillType.OTHER_FEE_FIXED;
                break;
            case "OTHER_FEE_CALCULATED":
            case "Прочий платеж рассчитанный":
                entity = BillType.OTHER_FEE_CALCULATED;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + string);
        }
        return entity;
    }
}
