package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.domodel.dto.AppealDto;
import ru.geekbrains.domodel.dto.AppealRequest;
import ru.geekbrains.domodel.entities.Appeal;
import ru.geekbrains.domodel.entities.AppealStatus;
import ru.geekbrains.domodel.exceptions.EntityNotFoundException;
import ru.geekbrains.domodel.mappers.AppealMapper;
import ru.geekbrains.domodel.repositories.AppealRepository;
import ru.geekbrains.domodel.services.api.AppealService;
import ru.geekbrains.domodel.services.api.PhotoService;
import ru.geekbrains.domodel.services.api.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.geekbrains.domodel.entities.constants.Messages.ENTITY_NOT_FOUND_BY_ID;
import static ru.geekbrains.domodel.entities.constants.Roles.hasAuthenticationRoleAdmin;

/**
 * Реализация сервиса обращений
 */
@Service
@RequiredArgsConstructor
public class AppealServiceImpl implements AppealService {

    // Репозиторий обращений
    private final AppealRepository appealRepository;

    // Необходимые сервисы и мапперы
    private final UserService userService;
    private final PhotoService photoService;
    private final AppealMapper appealMapper;

    @Override
    public AppealDto getDtoById(Long id,
                                Authentication authentication) {
        if (authentication != null) {
            Appeal appeal;
            Optional<Appeal> optionalAppeal = appealRepository.findById(id);
            if (optionalAppeal.isPresent()) {
                appeal = optionalAppeal.get();
                if (authentication.getName().equals(appeal.getAuthor().getUsername()) |
                        hasAuthenticationRoleAdmin(authentication)) {
                    return appealMapper.appealToAppealDto(appeal);
                }
            }
        }
        throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, id));
    }

    @Transactional
    @Override
    public AppealDto save(AppealRequest appealRequest, Authentication authentication) {
        // Если пользователь не авторизован
        if (authentication != null) {
            Appeal appeal = appealMapper.appealRequestToAppeal(appealRequest);
            // Добавляем дату
            appeal.setCreationDate(LocalDate.now());
            // Добавляем статус
            appeal.setStatus(AppealStatus.SENT);
            // Добавляем автора обращения
            appeal.setAuthor(userService.getByUsername(authentication.getName()));
            // Преобразуем список фотографий
            appeal.setPhotoLinks(photoService.saveAll(appealRequest.getPhotoLinks()));
            return appealMapper.appealToAppealDto(appealRepository.save(appeal));
        }
        return null;
    }

    @Override
    public AppealDto update(AppealDto appealDto, Authentication authentication) {
        // Если пользователь не авторизован
        if (authentication != null) {
            Optional<Appeal> optionalAppeal = appealRepository.findById(appealDto.getId());
            if (optionalAppeal.isPresent()) {
                Appeal appeal = optionalAppeal.get();
                if ((authentication.getName().equals(appeal.getAuthor().getUsername()) |
                        hasAuthenticationRoleAdmin(authentication))) {
                    appealMapper.updateAppeal(appeal, appealDto);
                    return appealMapper.appealToAppealDto(appealRepository.save(appeal));
                }
            }
        }
        return null;
    }

    @Override
    public List<AppealDto> getAllDtoByUser(Authentication authentication) {
        if (authentication == null) { // Если пользователь не авторизован
            return new ArrayList<>();
        } else {
            List<Appeal> appealList = appealRepository.findAllByAuthorUsername(authentication.getName()).stream()
                    .sorted((a1, a2) -> a2.getId().compareTo(a1.getId())).collect(Collectors.toList());
            return appealMapper.appealToAppealDto(appealList);
        }
    }

    @Override
    public List<AppealDto> getAllDto(Authentication authentication) {
        if (hasAuthenticationRoleAdmin(authentication)) { // Если пользователь не Администратор
            List<Appeal> appealList = appealRepository.findAll().stream()
                    .sorted((a1, a2) -> a2.getId().compareTo(a1.getId())).collect(Collectors.toList());
            return appealMapper.appealToAppealDto(appealList);
        } else {
            return new ArrayList<>();
        }
    }
}
