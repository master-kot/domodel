package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Tariff;
import ru.geekbrains.domodel.repositories.TariffRepository;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.TariffService;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса тарифов
 */
@Service
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {

    // Репозиторий тарифов
    private final TariffRepository tariffRepository;

    @Override
    public List<Tariff> getAllTariffs() {
        return tariffRepository.findAll();
    }

    @Override
    public Optional<Tariff> getById(Integer id) {
        return tariffRepository.findById(id);
    }
}
