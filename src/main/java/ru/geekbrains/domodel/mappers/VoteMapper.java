package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.entities.Vote;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Vote и VoteDto друг в друга
 */
@Mapper(componentModel = "spring", uses = {PhotoLinkMapper.class})
public interface VoteMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="title", source = "entity.title"),
            @Mapping(target="text", source = "entity.text"),
            @Mapping(target="startDate", source = "entity.startDate"),
            @Mapping(target="endDate", source = "entity.endDate"),
            @Mapping(target="photoLinks", source = "entity.photoLinks")
    })
    VoteDto voteToVoteDto(Vote entity);

    List<VoteDto> voteToVoteDto(List<Vote> entities);

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="title", source="dto.title"),
            @Mapping(target="text", source="dto.text"),
            @Mapping(target="startDate", source="dto.startDate"),
            @Mapping(target="endDate", source="dto.endDate"),
            @Mapping(target="photoLinks", ignore = true)
    })
    Vote voteDtoToVote(VoteDto dto);
}
