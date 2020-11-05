package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeterDataDto {

    @ApiModelProperty(value = "id показания")
    private Long meterDataId;

    @ApiModelProperty(value = "Дата подачи показания")
    private LocalDate creationDate;

    @NotNull
    @Positive
    @Digits(integer = 5, fraction = 3)
    @ApiModelProperty(value = "Значения показания")
    private Double value;
}
