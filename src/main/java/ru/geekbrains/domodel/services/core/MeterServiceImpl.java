package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.repositories.MeterDataRepository;
import ru.geekbrains.domodel.repositories.MeterRepository;
import ru.geekbrains.domodel.repositories.UserRepository;
import ru.geekbrains.domodel.services.api.MeterService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса счетчиков показаний
 */
@Service
@RequiredArgsConstructor
public class MeterServiceImpl implements MeterService {

    private final MeterRepository meterRepository;
    private final MeterDataRepository meterDataRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<Meter> getMeter(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(
                () -> new NullPointerException("User not found!")
        );
        return meterRepository.findByUser(user);
    }

    @Override
    public Optional<List<Meter>> getAllMeters() {
        return Optional.of(meterRepository.findAll());
    }

    @Override
    public Optional<List<Meter>> getMetersByUser(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(
                () -> new NullPointerException("User not found!")
        );
        return meterRepository.findAllByUser(user);
    }

    @Override
    public Meter findMeterByNum(Integer meterNum) {
        return meterRepository.findByMeterNumber(meterNum).orElseThrow(
                () -> new NullPointerException("Meter not found!")
        );
    }

    @Transactional
    @Override
    public void save(Meter meter, String name) {
        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new NullPointerException("User not found!")
        );
        meter.setUser(user);
        meterRepository.save(meter);
    }

    @Override
    public Optional<List<MeterData>> getAllMeterData(Meter meter) {
        return meterDataRepository.findByMeter(meter);
    }
}
