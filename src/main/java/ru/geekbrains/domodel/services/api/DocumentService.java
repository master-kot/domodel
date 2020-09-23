package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.DocumentDto;

import java.util.List;

/**
 * Сервис документов
 */
public interface DocumentService {

    /**
     * Вернуть список документов
     */
    List<DocumentDto> getAllDto();
}
