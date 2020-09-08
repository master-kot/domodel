package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.RequisitesDto;
import ru.geekbrains.domodel.repositories.RequisitesRepository;
import ru.geekbrains.domodel.services.api.RequisitesService;

/**
 * Реализация сервиса реквизитов компании
 */
@Service
@RequiredArgsConstructor
public class RequisitesServiceImpl implements RequisitesService {

    // Репозиторий реквизитов
    private final RequisitesRepository requisitesRepository;

    @Override
    public RequisitesDto getRelevantDto() {
        return null; // мапим результат requisitesRepository.findAll();
    }
}
