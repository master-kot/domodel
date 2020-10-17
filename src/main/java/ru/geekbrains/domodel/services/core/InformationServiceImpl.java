package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.InformationDto;
import ru.geekbrains.domodel.dto.InformationRequest;
import ru.geekbrains.domodel.entities.Information;
import ru.geekbrains.domodel.exceptions.EntityNotFoundException;
import ru.geekbrains.domodel.mappers.InformationMapper;
import ru.geekbrains.domodel.repositories.InformationRepository;
import ru.geekbrains.domodel.services.api.InformationService;

import java.util.List;
import java.util.Optional;

import static ru.geekbrains.domodel.entities.constants.Messages.ENTITY_NOT_FOUND_BY_ID;

/**
 * Реализация сервиса информации о компании
 */
@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {

    //
    private final InformationMapper informationMapper;

    // Репозиторий информации о компании
    private final InformationRepository informationRepository;

    @Override
    public InformationDto getDtoById(Integer id) {
        Optional<Information> optionalInformation = informationRepository.findById(id);
        return optionalInformation.map(informationMapper::informationToInformationDto).orElseThrow(
                () -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, id)));
    }

    @Override
    public List<InformationDto> getAllDto() {
        return informationMapper.informationToInformationDto(informationRepository.findAll());
    }

    @Override
    public InformationDto save(InformationRequest informationRequest) {
        Information information = informationMapper.informationRequestToInformation(informationRequest);
        return informationMapper.informationToInformationDto(informationRepository.save(information));
    }

    @Override
    public InformationDto update(InformationDto informationDto) {
        Optional<Information> optional = informationRepository.findById(informationDto.getId());
        if (optional.isPresent()) {
            Information information = informationMapper.updateInformation(optional.get(), informationDto);
            return informationMapper.informationToInformationDto(informationRepository.save(information));
        } else {
            return null;
        }
    }
}
