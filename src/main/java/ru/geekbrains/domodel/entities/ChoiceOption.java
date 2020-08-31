package ru.geekbrains.domodel.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Перечисление вариантов ответ на вопрос голосования
 */
@Getter
@RequiredArgsConstructor
public enum ChoiceOption {

    AGREED("За"),
    DISAGREED("Против"),
    ABSTAINED("Воздержаться");

    // Описание варианта ответа на вопрос
    private final String option;
}
