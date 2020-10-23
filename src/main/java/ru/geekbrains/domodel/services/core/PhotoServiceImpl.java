package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.PhotoLink;
import ru.geekbrains.domodel.mappers.PhotoLinkMapper;
import ru.geekbrains.domodel.repositories.PhotoRepository;
import ru.geekbrains.domodel.services.api.PhotoService;

import java.util.List;

/**
 * Реализация сервиса фотографий
 */
@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    // Репозиторий фотографий
    private final PhotoRepository photoRepository;

    // Необходимые сервисы и мапперы
    private final PhotoLinkMapper photoLinkMapper;

    @Override
    public List<PhotoLink> saveAll(List<String> photoLinks) {
        return photoRepository.saveAll(photoLinkMapper.stringToPhotoLink(photoLinks));
    }
}
