package ru.geekbrains.domodel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Transient;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.services.api.UserService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Представление сущности новости для работы с фронтэндом
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
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

    //todo с автором надо покумекать, по идее Имя юзера + фамилия
    @NotBlank
    private String userName;

    public NewsDto(News news) {
        this.creationDate = news.getCreationDate();
        this.title = news.getTitle();
        this.fullText = news.getFullText();
        this.pictureLink = news.getPictureLink();
        this.hidden = news.isHidden();
        this.pinned = news.isPinned();
        this.visible = news.isVisible();
        this.shortText = news.getFullText().substring(0, 255);
        this.userName = news.getAuthorId().getUsername();
    }
}
