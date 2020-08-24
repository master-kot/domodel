package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.repositories.TariffRepository;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.TariffService;

/**
 * Реализация сервиса тарифов
 */
@Service
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {

    // Репозиторий пользователей
    private final TariffRepository tariffRepository;


}
