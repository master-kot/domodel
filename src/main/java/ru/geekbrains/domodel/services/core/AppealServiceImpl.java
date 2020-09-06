package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.AppealDto;
import ru.geekbrains.domodel.dto.AppealRequest;
import ru.geekbrains.domodel.entities.Appeal;
import ru.geekbrains.domodel.entities.AppealStatus;
import ru.geekbrains.domodel.mappers.AppealMapper;
import ru.geekbrains.domodel.repositories.AppealRepository;
import ru.geekbrains.domodel.services.api.AppealService;
import ru.geekbrains.domodel.services.api.PhotoService;
import ru.geekbrains.domodel.services.api.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;

/**
 * Реализация сервиса обращений
 */
@Service
@RequiredArgsConstructor
public class AppealServiceImpl implements AppealService {

    // Необходимые сервисы
    private final AppealMapper appealMapper;
    private final UserService userService;
    private final PhotoService photoService;

    // Репозиторий обращений
    private final AppealRepository appealRepository;

    @Override
    public AppealDto getById(Long id,
                             Authentication authentication) {
        if (authentication != null) {
            Appeal appeal;
            Optional<Appeal> optionalAppeal = appealRepository.findById(id);
            if (optionalAppeal.isPresent()) {
                appeal = optionalAppeal.get();
                if (authentication.getName().equals(appeal.getAuthorId().getUsername()) |
                        hasAuthenticationRoleAdmin(authentication)) {
                    return appealMapper.appealToAppealDto(appeal);
                }
            }
        }
        return null;
    }

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
            appeal.setAuthorId(userService.getUserByUsername(authentication.getName()));
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
                if ((authentication.getName().equals(appeal.getAuthorId().getUsername()) |
                        hasAuthenticationRoleAdmin(authentication))) {
                    appeal.setTitle(appealDto.getTitle());
                    appeal.setText(appealDto.getText());
                    appeal.setPhoneNumber(appealDto.getPhoneNumber());
                    appeal.setStatus(AppealStatus.valueOf(appealDto.getStatus()));
                    // TODO реализовать изменение фотографий
                    return appealMapper.appealToAppealDto(appealRepository.save(appeal));
                }
            }
        }
        return null;
    }

    @Override
    public List<AppealDto> getAll(Authentication authentication) {
        // Если пользователь не авторизован
        if (authentication == null) {
            return new ArrayList<AppealDto>();
        }
        // Если пользователь - админ
        if (hasAuthenticationRoleAdmin(authentication)) {
            return mapEntityListToDtoList(appealRepository.findAll());
        } else { // Просто пользователь
            return mapEntityListToDtoList(appealRepository.findAllByAuthorIdUsername(authentication.getName()));
        }
    }

    /**
     * Проверить, что пользователь имеет роль Админа
     */
    private boolean hasAuthenticationRoleAdmin(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_ADMIN)));
    }

    /**
     * Преобразовать список сущностей к их DTO представлениям
     */
    private List<AppealDto> mapEntityListToDtoList(Collection<Appeal> appealList) {
        return appealList.stream().map(appealMapper::appealToAppealDto).collect(Collectors.toList());
    }
}
