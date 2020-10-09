package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.DocumentDto;
import ru.geekbrains.domodel.entities.Document;
import ru.geekbrains.domodel.mappers.DocumentMapper;
import ru.geekbrains.domodel.repositories.DocumentRepository;
import ru.geekbrains.domodel.services.api.DocumentService;

import java.util.List;

/**
 * Имплиментация сервиса документов
 */
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    // Необходимые сервисы и репозитории
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    @Override
    public List<DocumentDto> getAllDto() {
        return documentMapper.documentToDocumentDto(documentRepository.findAll());
    }

    @Override
    public DocumentDto save(DocumentDto documentRequest) {
        Document document = documentMapper.documentDtoToDocument(documentRequest);
        return documentMapper.documentToDocumentDto(documentRepository.save(document));
    }
}
