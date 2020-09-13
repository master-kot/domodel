package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.dto.NewsRequest;
import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.dto.VoteRequest;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.entities.Vote;

@Mapper(componentModel = "spring")
public interface VoteMapper {
    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="startDate", source = "entity.startDate",
                    dateFormat = "dd-MM-yyyy"),
            @Mapping(target="endDate", source = "entity.endDate",
                    dateFormat = "dd-MM-yyyy"),
            @Mapping(target="title", source = "entity.title"),
            @Mapping(target="text", source = "entity.text"),
            @Mapping(target="photoLinks", source = "entity.photoLinks"),
            @Mapping(target="documents", source = "entity.documents"),
            @Mapping(target="voteDatas", source = "entity.voteDatas"),

           // @Mapping(target="authorName", source = "entity.author.firstName")
    }) //todo добавить результаты
    VoteDto votesToVoteDto(Vote entity);

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="startDate", source="dto.startDate",
                    dateFormat="dd-MM-yyyy"),
            @Mapping(target="endDate", source="dto.endDate",
                    dateFormat="dd-MM-yyyy"),
            @Mapping(target="title", source="dto.title"),
            @Mapping(target="text", source="dto.text"),
            @Mapping(target="photoLinks", source="dto.photoLinks"),
            @Mapping(target="documents", source="dto.documents"),
            @Mapping(target="voteDatas", source="dto.voteDatas")})
    Vote voteDtoToVote(VoteDto dto);

    @Mappings({
            @Mapping(target="title", source="dto.title"),
            @Mapping(target="text", source="dto.text"),
            @Mapping(target="startDate", source="dto.startDate",
                    dateFormat="dd-MM-yyyy"),
            @Mapping(target="endDate", source="dto.endDate",
                    dateFormat="dd-MM-yyyy"),
            @Mapping(target="photoLinks", source="dto.photoLinks"),
            @Mapping(target="documents", source="dto.documents")})
    Vote voteRequestToVote(VoteRequest dto);
}
