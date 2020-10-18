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
import java.util.stream.Collectors;

import static ru.geekbrains.domodel.entities.constants.Messages.ENTITY_NOT_FOUND_BY_ID;

/**
 * Реализация сервиса информации о компании
 */
@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {

    // Репозиторий информации о компании
    private final InformationRepository informationRepository;

    // Необходимые сервисы и мапперы
    private final InformationMapper informationMapper;

    @Override
    public InformationDto getDtoById(Integer id) {
        Optional<Information> optionalInformation = informationRepository.findById(id);
        return optionalInformation.map(informationMapper::informationToInformationDto).orElseThrow(
                () -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, id)));
    }

    @Override
    public List<InformationDto> getAllDto() {
        List<Information> informationList = informationRepository.findAll().stream()
                .sorted((i1, i2) -> i2.getId().compareTo(i1.getId())).collect(Collectors.toList());
        return informationMapper.informationToInformationDto(informationList);
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
