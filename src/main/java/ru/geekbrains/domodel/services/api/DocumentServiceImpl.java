package ru.geekbrains.domodel.services.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.DocumentDto;
import ru.geekbrains.domodel.mappers.DocumentMapper;
import ru.geekbrains.domodel.repositories.DocumentRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Имплиментация сервиса документов
 */
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private DocumentMapper documentMapper;

    @Override
    public List<DocumentDto> getAll() {
        return documentRepository.findAll().stream()
                .map(documentMapper::documentToDocumentDto).collect(Collectors.toList());
    }
}
