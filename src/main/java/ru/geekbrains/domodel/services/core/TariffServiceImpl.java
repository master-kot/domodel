package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.TariffDto;
import ru.geekbrains.domodel.repositories.TariffRepository;
import ru.geekbrains.domodel.services.api.TariffService;

import java.util.List;

/**
 * Реализация сервиса тарифов
 */
@Service
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {

    // Репозиторий тарифов
    private final TariffRepository tariffRepository;

    @Override
    public List<TariffDto> getAllTariffs() {
        return null; // мапим результат tariffRepository.findAll();
    }

    @Override
    public TariffDto getTariffById(Integer id) {
        return null; // мапим результат tariffRepository.findById(id);
    }
}
