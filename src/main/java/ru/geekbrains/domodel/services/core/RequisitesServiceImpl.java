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

    @Override
    public RequisitesDto getCurrentDto() {
        return requisitesRepository.findById(CURRENT_ID_NUMBER)
                .map(RequisitesMapper::requisitesToRequisitesDto).orElse(null);
    }

    @Override
    public RequisitesDto update(RequisitesDto requisitesDto) {
        Optional<Requisites> optional = requisitesRepository.findById(CURRENT_ID_NUMBER);
        Requisites requisites;
        if (optional.isPresent()) {
            requisites = optional.get();
            requisites.setCompanyName(requisitesDto.getCompanyName());
            requisites.setInn(requisitesDto.getInn());
            requisites.setKpp(requisitesDto.getKpp());
            requisites.setOgrn(requisitesDto.getOgrn());
            requisites.setBankAccount(requisitesDto.getBankAccount());
            requisites.setBankName(requisitesDto.getBankName());
            requisites.setBik(requisitesDto.getBik());
            requisites.setCorrespondentAccount(requisitesDto.getCorrespondentAccount());
        } else {
            requisites = RequisitesMapper.requisitesDtoToRequisites(requisitesDto);
        }
        return RequisitesMapper.requisitesToRequisitesDto(requisitesRepository.save(requisites));
    }
}
