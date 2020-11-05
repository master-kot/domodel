package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.RequisitesDto;
import ru.geekbrains.domodel.entities.Requisites;
import ru.geekbrains.domodel.exceptions.EntityNotFoundException;
import ru.geekbrains.domodel.mappers.RequisitesMapper;
import ru.geekbrains.domodel.repositories.RequisitesRepository;
import ru.geekbrains.domodel.services.api.RequisitesService;

import static ru.geekbrains.domodel.entities.constants.Messages.ENTITY_NOT_FOUND;

/**
 * Реализация сервиса реквизитов компании
 */
@Service
@RequiredArgsConstructor
public class RequisitesServiceImpl implements RequisitesService {

    // Репозиторий реквизитов
    private final RequisitesRepository requisitesRepository;

    // Необходимые сервисы и мапперы
    private final RequisitesMapper requisitesMapper;

    @Override
    public RequisitesDto getCurrentDto() {
        return requisitesMapper.requisitesToRequisitesDto(getCurrentRequisites());
    }

    @Override
    public RequisitesDto update(RequisitesDto requisitesDto) {
        Requisites requisites = requisitesMapper.updateRequisites(getCurrentRequisites(), requisitesDto);
        return requisitesMapper.requisitesToRequisitesDto(requisitesRepository.save(requisites));
    }

    /**
     * Получает текущие реквизиты
     */
    private Requisites getCurrentRequisites() {
        return requisitesRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND));
    }
}
