package ru.geekbrains.domodel.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.repositories.MeterRepository;
import ru.geekbrains.domodel.services.api.MeterService;

import java.util.List;

/**
 * Реализация сервиса счетчиков показаний
 */
@Service
public class MeterServiceImpl implements MeterService {

    private final MeterRepository meterRepository;

    @Autowired
    public MeterServiceImpl(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    @Override
    public List<Meter> getAllMeters() {
        return meterRepository.findAll();
    }
}
