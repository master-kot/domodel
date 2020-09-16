package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.RequisitesDto;
import ru.geekbrains.domodel.mappers.RequisitesMapper;
import ru.geekbrains.domodel.repositories.RequisitesRepository;
import ru.geekbrains.domodel.services.api.RequisitesService;

/**
 * Реализация сервиса реквизитов компании
 */
@Service
@RequiredArgsConstructor
public class RequisitesServiceImpl implements RequisitesService {

    private final Integer CURRENT_ID_NUMBER = 1;

    // Репозиторий реквизитов
    private final RequisitesRepository requisitesRepository;

    @Override
    public RequisitesDto getCurrentDto() {
        return requisitesRepository.findById(CURRENT_ID_NUMBER)
                .map(RequisitesMapper::requisitesToRequisitesDto).orElse(null);
    }
}
