package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmitDataDto {

    @ApiModelProperty(value = "id счетчика")
    private Long meterId;

    @ApiModelProperty(value = "Значения показания")
    private Double value;

    @ApiModelProperty(value = "Дата подачи показания")
    private LocalDate creationDate;
}
