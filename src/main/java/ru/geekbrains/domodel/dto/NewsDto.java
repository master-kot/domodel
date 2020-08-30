package ru.geekbrains.domodel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Transient;
import ru.geekbrains.domodel.entities.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Представление сущности новости для работы с фронтэндом
 */
@Getter
@Setter
@NoArgsConstructor
public class NewsDto {
    @NotBlank
    @CreatedDate
    private LocalDate creationDate;

    @NotBlank
    private String title;

    @NotBlank
    private String fullText;

    @NotBlank
    @Transient
    @Size (max = 255)
    private String shortText = fullText.substring(0, 255);

    @NotBlank
    private String pictureLink;

    @NotBlank
    private boolean hidden;

    @NotBlank
    private boolean pinned;

    @NotBlank
    private boolean visible;

    //todo с автором надо покумекать
    @NotBlank
    private User authorId;
}
