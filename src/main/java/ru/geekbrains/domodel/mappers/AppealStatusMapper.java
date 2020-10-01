package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import ru.geekbrains.domodel.entities.AppealStatus;

/**
 * Маппер, преобразовывающий классы AppealStatus и String друг в друга
 */
@Mapper(componentModel = "spring")
public interface AppealStatusMapper {

    default String appealStatusToString(AppealStatus entity) {
        return entity.getDescription();
    }

    default AppealStatus stringToAppealStatus(String string) {
        AppealStatus entity;
        switch (string) {
            case "SENT" :
            case "Направлено":
                entity = AppealStatus.SENT;
                break;
            case "RECEIVED":
            case "Получено":
                entity = AppealStatus.RECEIVED;
                break;
            case "IN_PROGRESS":
            case "В работе":
                entity = AppealStatus.IN_PROGRESS;
                break;
            case "DONE":
            case "Выполнено":
                entity = AppealStatus.DONE;
                break;
            case "CANCELLED":
            case "Отменено":
                entity = AppealStatus.CANCELLED;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + string);
        }
        return entity;
    }
}
