package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Information;
import ru.geekbrains.domodel.repositories.InformationRepository;
import ru.geekbrains.domodel.services.api.InformationService;

import java.util.Optional;

/**
 * Реализация сервиса информации о компании
 */
@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {

    // Репозиторий информации о компании
    private final InformationRepository informationRepository;

    @Override
    public Optional<Information> getById(Integer id) {
        return informationRepository.findById(id);
    }
}
