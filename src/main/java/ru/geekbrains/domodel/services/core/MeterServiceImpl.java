package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.MeterDto;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
import ru.geekbrains.domodel.repositories.MeterDataRepository;
import ru.geekbrains.domodel.repositories.MeterRepository;
import ru.geekbrains.domodel.repositories.MeterTypeRepository;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.MeterService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Реализация сервиса счетчиков показаний
 */
@Service
@RequiredArgsConstructor
public class MeterServiceImpl implements MeterService {

    private final MeterRepository meterRepository;
    private final MeterDataRepository meterDataRepository;
    private final MeterTypeRepository meterTypeRepository;

    private final AccountService accountService;

    @Override
    public MeterDto getMeter(Long id) {
        Meter m = meterRepository.findById(id).orElseThrow(() -> new RuntimeException("not found meter by id: " + id));

        return MeterDto.builder()
                .id(m.getId())
                .serialNumber(m.getSerialNumber())
                .model(m.getModel())
                .checkDate(m.getCheckDate())
                .houseNumber(m.getAccount().getHouseNumber())
                .accountId(m.getAccount().getId())
                .typeDescription(m.getType().getDescription())
                //TODO:убрать Option
//                    .currentMeterData(getCurrentMeterDataByMeter(m).get().getValue())
                .tariffDescription(m.getType().getTariff().getDescription())
                .build();
    }

    @Override
    public void deleteMeter(Long id) {
        meterRepository.deleteById(id);
    }

    @Override
    public List<Meter> getAllMetersByAccount(Account account) {
        return meterRepository.findByAccount(account);
    }

    @Override
    public List<MeterDto> getAllMeters() {
        return meterRepository.findAll().stream().map(m -> MeterDto.builder()
                .id(m.getId())
                .serialNumber(m.getSerialNumber())
                .model(m.getModel())
                .checkDate(m.getCheckDate())
                .houseNumber(m.getAccount().getHouseNumber())
                .accountId(m.getAccount().getId())
                .typeDescription(m.getType().getDescription())
                //TODO:убрать Option
//                    .currentMeterData(getCurrentMeterDataByMeter(m).get().getValue())
                .tariffDescription(m.getType().getTariff().getDescription())
                .build()).collect(Collectors.toList());
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
        meterData.setCreationDate(LocalDate.now());
        Optional<MeterData> current = getCurrentMeterDataByMeter(meterData.getMeter());
        if (current.isPresent()) {
            if (current.get().getCreationDate().getMonth().equals(meterData.getCreationDate().getMonth())) {
                meterData.setId(current.get().getId());
            }
        }
        meterDataRepository.save(meterData);
    }

    @Transactional
    @Override
    public MeterDto save(MeterDto meterDto) {
        Meter meter = new Meter();
        meter.setAccount(accountService.getAccountById(meterDto.getAccountId()));
        meter.setCheckDate(meterDto.getCheckDate());
        meter.setModel(meterDto.getModel());
        meter.setSerialNumber(meterDto.getSerialNumber());
        meter.setType(meterTypeRepository.findByDescription(meterDto.getTypeDescription()));
        return convertToDto(meterRepository.save(meter));
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

    @Override
    public void generateDefaultMeterData() {
//        LocalDate date = LocalDate.now();
//        int month = date.getMonthValue();
//        List<Meter> meters = getAllMeters();
//
//        if (!meters.isEmpty()) {
//            for (Meter meter : meters) {
//                if (getCurrentMeterDataByMeter(meter).isPresent()
//                        && getCurrentMeterDataByMeter(meter).get().getCreationDate().getMonth().getValue() == month) {
//                    log.info(String.format("показания %s уже поданны", meter));
//                } else {
//                    MeterData meterData = new MeterData(meter, date, meter.getTariff().getDefaultIncreaseValue(), true);
//                    meterDataRepository.save(meterData);
//                    log.info(String.format("новые показания для %s : %s", meter.getSerialNumber(), meterData));
//                }
//            }
//        }
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

    //TODO: перенести в Utils или подключить библиотеку конвертора
    private MeterDto convertToDto(Meter m) {
        return MeterDto.builder()
                .id(m.getId())
                .serialNumber(m.getSerialNumber())
                .model(m.getModel())
                .checkDate(m.getCheckDate())
                .houseNumber(m.getAccount().getHouseNumber())
                .accountId(m.getAccount().getId())
                .typeDescription(m.getType().getDescription())
                //TODO:убрать Option
//                    .currentMeterData(getCurrentMeterDataByMeter(m).get().getValue())
                .tariffDescription(m.getType().getTariff().getDescription())
                .build();
    }
}
