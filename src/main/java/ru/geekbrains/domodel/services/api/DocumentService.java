package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.DocumentDto;

import java.util.List;

/**
 * Сервис документов
 */
public interface DocumentService {

    /**
     * Выводит список всех документов
     */
    List<DocumentDto> getAllDto();

    /**
     * Создает документ
     */
    DocumentDto save(DocumentDto documentRequest);
}
