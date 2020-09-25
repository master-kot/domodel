package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

/**
 * Dto представление Голосования
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoteDto {

    private Long id;

    private String title;

    private String text;

    private LocalDate startDate;

    private LocalDate endDate;

//    private List<PhotoLink> photoLinks = new ArrayList<>();

//    private List<Document> documents = new ArrayList<>();

//    private List<VoteData> voteDatas = new ArrayList<>();
}
