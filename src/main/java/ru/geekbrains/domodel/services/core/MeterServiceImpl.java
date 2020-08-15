package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
import ru.geekbrains.domodel.repositories.MeterDataRepository;
import ru.geekbrains.domodel.repositories.MeterRepository;
import ru.geekbrains.domodel.services.api.MeterService;

import javax.transaction.Transactional;
import java.util.Date;
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

    @Override
    public Optional<List<Meter>> getAllMetersByAccount(Account account) {
        return meterRepository.findByAccount(account);
    }

    @Override
    public Optional<List<Meter>> getAllMeters() {
        return Optional.of(meterRepository.findAll());
    }

    @Override
    public Meter getMeterBySerialNumber(Integer serialNumber) {
        return meterRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new NullPointerException("Meter not found!")
        );
    }

    @Transactional
    @Override
    public void submitMeterData(MeterData meterData) {
        meterData.setCreationDate(new Date());
        meterDataRepository.save(meterData);
    }

    @Transactional
    @Override
    public void save(Meter meter) {
        meterRepository.save(meter);
    }

    @Override
    public Optional<List<MeterData>> getAllMeterDataByMeter(Meter meter) {
        return meterDataRepository.findByMeter(meter);
    }

    //TODO реализовать получение показаний для счетчика
    @Override
    public Optional<MeterData> getPreviousMeterDataByMeter(Meter meter) {
        return Optional.empty();
    }

    //TODO реализовать получение показаний для списка счетчиков
    @Override
    public Optional<List<MeterData>> getPreviousMeterDatasByMeters(List<Meter> meter) {
        return Optional.empty();
    }

    //TODO реализовать получение показаний для счетчика
    @Override
    public Optional<MeterData> getCurrentMeterDataByMeter(Meter meter) {
        return Optional.empty();
    }

    //TODO реализовать получение показаний для списка счетчиков
    @Override
    public Optional<List<MeterData>> getCurrentMeterDatasByMeters(List<Meter> meter) {
        return Optional.empty();
    }
}
