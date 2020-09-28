package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.RequisitesDto;
import ru.geekbrains.domodel.entities.Requisites;
import ru.geekbrains.domodel.mappers.RequisitesMapper;
import ru.geekbrains.domodel.repositories.RequisitesRepository;
import ru.geekbrains.domodel.services.api.RequisitesService;

import java.util.Optional;

/**
 * Реализация сервиса реквизитов компании
 */
@Service
@RequiredArgsConstructor
public class RequisitesServiceImpl implements RequisitesService {

    private final Integer CURRENT_ID_NUMBER = 1;

    // Репозиторий реквизитов
    private final RequisitesRepository requisitesRepository;
    // Маппер
    RequisitesMapper requisitesMapper;

    @Override
    public RequisitesDto getCurrentDto() {
        return requisitesRepository.findById(CURRENT_ID_NUMBER)
                .map(requisitesMapper::requisitesToRequisitesDto).orElse(null);
    }

    @Override
    public RequisitesDto update(RequisitesDto requisitesDto) {
        Optional<Requisites> optional = requisitesRepository.findById(CURRENT_ID_NUMBER);
        Requisites requisites;
        if (optional.isPresent()) {
            requisites = requisitesMapper.updateRequisites(optional.get(), requisitesDto);
        } else {
            requisites = requisitesMapper.requisitesDtoToRequisites(requisitesDto);
        }
        return requisitesMapper.requisitesToRequisitesDto(requisitesRepository.save(requisites));
    }
}
