package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.InformationDto;
import ru.geekbrains.domodel.repositories.InformationRepository;
import ru.geekbrains.domodel.services.api.InformationService;

/**
 * Реализация сервиса информации о компании
 */
@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {

    // Репозиторий информации о компании
    private final InformationRepository informationRepository;

    @Override
    public InformationDto getInformationById(Integer id) {
        return null; // мапим результат informationRepository.findById(id);
    }
}
