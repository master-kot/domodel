package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import ru.geekbrains.domodel.dto.AppealDto;
import ru.geekbrains.domodel.dto.AppealRequest;
import ru.geekbrains.domodel.entities.Appeal;
import ru.geekbrains.domodel.entities.AppealStatus;
import ru.geekbrains.domodel.entities.PhotoLink;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * Маппер, преобразовывающий классы Appeal и AppealDto друг в друга
 */
@Mapper(componentModel = "spring")
public abstract class AppealMapper {

    public AppealDto appealToAppealDto(Appeal entity) {
        AppealDto dto = new AppealDto();
        dto.setId(entity.getId());
        dto.setCreationDate(entity.getCreationDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        dto.setTitle(entity.getTitle());
        dto.setText(entity.getText());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setStatus(entity.getStatus().getDescription());
        dto.setPhotoLinks(entity.getPhotoLinks().stream()
                        .map(PhotoLink::getLinkAddress).collect(Collectors.toList()));
        return dto;
    }

    public Appeal appealDtoToAppeal(AppealDto dto) {
        Appeal entity = new Appeal();
        entity.setCreationDate(LocalDate.parse(dto.getCreationDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setText(dto.getText());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setStatus(AppealStatus.valueOf(dto.getStatus()));
        return entity;
    }

    public Appeal appealRequestToAppeal(AppealRequest dto) {
        Appeal entity = new Appeal();
        entity.setTitle(dto.getTitle());
        entity.setText(dto.getText());
        entity.setPhoneNumber(dto.getPhoneNumber());
        return entity;
    }
}
