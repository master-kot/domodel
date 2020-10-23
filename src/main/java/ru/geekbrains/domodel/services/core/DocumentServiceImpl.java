package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.DocumentDto;
import ru.geekbrains.domodel.entities.Document;
import ru.geekbrains.domodel.mappers.DocumentMapper;
import ru.geekbrains.domodel.repositories.DocumentRepository;
import ru.geekbrains.domodel.services.api.DocumentService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Имплиментация сервиса документов
 */
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    // Репозиторий документов
    private final DocumentRepository documentRepository;

    // Необходимые сервисы и мапперы
    private final DocumentMapper documentMapper;

    @Override
    public List<DocumentDto> getAllDto() {
        List<Document> documentList = documentRepository.findAll().stream()
                .sorted((d1, d2) -> d2.getId().compareTo(d1.getId())).collect(Collectors.toList());
        return documentMapper.documentToDocumentDto(documentList);
    }

    @Override
    public DocumentDto save(DocumentDto documentRequest) {
        Document document = documentMapper.documentDtoToDocument(documentRequest);
        return documentMapper.documentToDocumentDto(documentRepository.save(document));
    }
}
