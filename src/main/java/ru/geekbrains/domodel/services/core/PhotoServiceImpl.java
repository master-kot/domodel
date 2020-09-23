package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.PhotoLink;
import ru.geekbrains.domodel.repositories.PhotoRepository;
import ru.geekbrains.domodel.services.api.PhotoService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса фотографий
 */
@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    // Репозиторий фотографий
    private final PhotoRepository photoRepository;

    @Override
    public List<PhotoLink> saveAll(List<String> photoLinks) {
        List<PhotoLink> photoLinkList = photoLinks.stream().map(PhotoLink::new).collect(Collectors.toList());
        return photoRepository.saveAll(photoLinkList);
    }
}
