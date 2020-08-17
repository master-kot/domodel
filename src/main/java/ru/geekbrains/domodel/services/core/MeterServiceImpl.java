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
import java.util.*;

/**
 * Реализация сервиса счетчиков показаний
 */
@Service
@RequiredArgsConstructor
public class MeterServiceImpl implements MeterService {

    private final MeterRepository meterRepository;
    private final MeterDataRepository meterDataRepository;

    @Override
    public Meter getMeter(Long id) {
        return meterRepository.findById(id).orElseThrow(() -> new RuntimeException("not found meter by id: " + id));
    }

    @Override
    public List<Meter> getAllMetersByAccount(Account account) {
        return meterRepository.findByAccount(account);
    }

    @Override
    public List<Meter> getAllMeters() {
        return meterRepository.findAll();
    }

    @Override
    public Meter getMeterBySerialNumber(Integer serialNumber) {
        return meterRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new NullPointerException("Meter not found!")
        );
    }

    //TODO реализовать сохранение показаний для счетчика согласно описанию в интерфейсе
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
    public List<MeterData> getAllMeterDataByMeter(Meter meter) {
        return meterDataRepository.findAllByMeterOrderByCreationDateDesc(meter);
    }

    //TODO реализовать получение показаний для счетчика (поискать лучшее вариант: сделать за "одно" обращение к бд)
    @Override
    public Optional<MeterData> getPreviousMeterDataByMeter(Meter meter) {
        if (meterDataRepository.findTopByMeterOrderByCreationDateDesc(meter).isPresent()) {
          MeterData md = meterDataRepository.findTopByMeterOrderByCreationDateDesc(meter).get();
          return meterDataRepository.findFirstByMeterAndCreationDateBefore(meter, md.getCreationDate());
        }
        return Optional.empty();
    }

    //TODO реализовать получение показаний для списка счетчиков
    @Override
    public List<MeterData> getPreviousMeterDatasByMeters(List<Meter> meter) {
        return new ArrayList<>();
    }

    @Override
    public Optional<MeterData> getCurrentMeterDataByMeter(Meter meter) {
        return meterDataRepository.findTopByMeterOrderByCreationDateDesc(meter);
    }

    //TODO реализовать получение показаний для списка счетчиков
    @Override
    public List<MeterData> getCurrentMeterDatasByMeters(List<Meter> meter) {
        return new ArrayList<>();
    }

    //TODO реализовать генерацию показаний для счетчиков с неподанными в данном периоде показаниями
    @Override
    public void generateDefaultMeterData() {
    }

    @Override
    public List<MeterData> getAllDataByMeters(List<Meter> meters) {
        List<MeterData> result = new ArrayList<>();
        for(Meter m : meters) {
            result.addAll(m.getMeterDatas());
        }
        result.sort((o1, o2) -> o2.getCreationDate().compareTo(o1.getCreationDate()));
        return result;
    }
}
