package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.*;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
import ru.geekbrains.domodel.entities.constants.Roles;
import ru.geekbrains.domodel.mappers.*;
import ru.geekbrains.domodel.repositories.MeterDataRepository;
import ru.geekbrains.domodel.repositories.MeterRepository;
import ru.geekbrains.domodel.repositories.MeterTypeRepository;
import ru.geekbrains.domodel.repositories.TariffRepository;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.MeterService;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Реализация сервиса счетчиков показаний
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MeterServiceImpl implements MeterService {

    private final MeterMapper meterMapper;
    private final MeterDataMapper dataMapper;
    private final AccountMapper accountMapper;
    private final MeterTypeMapper typeMapper;
    private final TariffMapper tariffMapper;

    private final MeterRepository meterRepository;
    private final MeterDataRepository meterDataRepository;
    private final MeterTypeRepository meterTypeRepository;
    private final TariffRepository tariffRepository;

    private final AccountService accountService;

    @Override
    public MeterDto getMeter(Long id) {
        Meter m = meterRepository.findById(id).orElseThrow(() -> new RuntimeException("not found meter by id: " + id));
        return meterMapper.meterToMeterDto(m);
    }

    @Transactional
    @Override
    public Integer deleteMeterById(Long id) {
        return meterRepository.deleteMeterById(id);
    }

    @Override
    public List<Meter> getAllMetersByAccount(Account account) {
        return meterRepository.findByAccount(account);
    }

    @Override
    public List<MeterDto> getAllMetersByUserName(String name) {
        List<Account> accounts = accountService.getAllAccountsByUserUsername(name);
        List<Meter> meters = meterRepository.findAllByAccountIn(accounts).orElse(new ArrayList<>());
        return meterMapper.meterToMeterDto(meters);
    }

    @Override
    public List<MeterDto> getAllMeters(Authentication authentication) {
        if (authentication != null) {
            if (Roles.hasAuthenticationRoleAdmin(authentication)) {
                List<Meter> meters = meterRepository.findAll();
                List<MeterDto> meterDtoList = meterMapper.meterToMeterDto(meters);
                List<MeterData> currentData = getCurrentMeterDataByMeters(meters);

                for (MeterDto m : meterDtoList) {
                    for (MeterData md : currentData) {
                        if (m.getMeterId().equals(md.getMeter().getId())) {
                            m.setCurrentMeterData(dataMapper.meterDataToMeterDataDto(md));
                            break;
                        }
                    }
                }

                return meterDtoList;
            }
            else {
                throw new RuntimeException("Нет нужны прав для работы со счетчиками: " + authentication);
            }
        }
        throw new RuntimeException("Нет нужны прав для работы со счетчиками: authentication -> null");
    }

    @Override
    public  List<AccountMetersDto> getMetersUser(Authentication authentication) {
        if (authentication != null) {
            List<Meter> meters;
            List<MeterData> currentData;
            List<MeterDto> meterDtoList;
            AccountMetersDto accountMetersDto;
            List<AccountMetersDto> result = new ArrayList<>();
            List<Account> accounts = accountService.getAllAccountsByUserUsername(authentication.getName());

            for (Account account : accounts) {
                meters = meterRepository.findByAccount(account);
                accountMetersDto = accountMapper.accountToAccountMetersDto(account);

                if (!meters.isEmpty()) {
                    meterDtoList = meterMapper.meterToMeterDto(meters);
                    currentData = getCurrentMeterDataByMeters(meters);

                    for (MeterDto m : meterDtoList) {
                        for(MeterData md : currentData) {
                            if (m.getMeterId().equals(md.getMeter().getId())) {
                                m.setCurrentMeterData(dataMapper.meterDataToMeterDataDto(md));
                                break;
                            }
                        }
                    }
                    accountMetersDto.setMeters(meterDtoList);
                }

                result.add(accountMetersDto);
            }
            return result;
        }
        throw new RuntimeException("Нет нужны прав для работы со счетчиками: authentication -> null");
    }

    @Override
    public Meter getMeterBySerialNumber(Integer serialNumber) {
        return meterRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new NullPointerException("Meter not found!")
        );
    }

    @Transactional
    @Override
    public MeterDataDto submitMeterData(Long meterId, Double submitData, Authentication authentication) {
        if (submitData != null && !submitData.isNaN()) {
            LocalDate nowDate = LocalDate.now();
            MeterData current;

          Meter meter = meterRepository.findById(meterId)
                  .orElseThrow(() -> new RuntimeException("Submit data - meter not found by id: " + meterId));

          if (!meter.getAccount().getUser().getUsername().equals(authentication.getName())) {
              log.error("Нет нужны прав для работы со счетчиками: счетчик не пренадлежит пользователю");
              return null;
          }

          MeterData previous = getCurrentMeterDataByMeter(meter);

          if (previous != null && previous.getCreationDate().getMonth().equals(nowDate.getMonth())) {
              previous.setValue(submitData);
              previous.setCreationDate(nowDate);
              return dataMapper.meterDataToMeterDataDto(meterDataRepository.save(previous));
          } else {
              current = MeterData.builder().creationDate(nowDate).meter(meter).value(submitData).build();
              return dataMapper.meterDataToMeterDataDto(meterDataRepository.save(current));
          }
        } else {
            log.warn("Показания не корректны");
            return null;
        }
    }

    @Transactional
    @Override
    public List<MeterDataDto> submitAllMeterData(List<SubmitDataDto> submitData, Authentication authentication) {
        if (!submitData.isEmpty()) {
            if (!Roles.hasAuthenticationRoleAdmin(authentication)) {
                throw new RuntimeException("Ошибка доступа");
            }

            MeterData current;
            MeterData previous;
            Optional<Meter> meter;
            LocalDate nowDate = LocalDate.now();
            List<MeterData> meterDatas = new ArrayList<>();

            for (SubmitDataDto sd : submitData) {

                if (sd.getValue().isNaN() || sd.getValue() == null) {
                    log.warn("Показания не корректны");
                    continue;
                }

                meter = meterRepository.findById(sd.getMeterId());

                if (!meter.isPresent()) {
                    log.warn("Показания не корректны: Submit data - meter not found by id: " + sd.getMeterId());
                    continue;
                }

                previous = getCurrentMeterDataByMeter(meter.get());

                if (previous != null && previous.getCreationDate().getMonth().equals(nowDate.getMonth())) {
                    previous.setValue(sd.getValue());
                    previous.setCreationDate(nowDate);
                    meterDatas.add(previous);
                } else {
                    current = MeterData.builder().creationDate(nowDate).meter(meter.get()).value(sd.getValue()).build();
                    meterDatas.add(current);
                }
            }

            return dataMapper.meterDataToMeterDataDto(meterDataRepository.saveAll(meterDatas));

        } else {
            throw new  RuntimeException("Показания не корректны");
        }
    }

    @Transactional
    @Override
    public MeterDto saveOrUpdate(Long idMeter, MeterDto meterDto) {
        Objects.requireNonNull(meterDto, "Данные счетчика не коректны!");

        if (meterDto.getSerialNumber() != null) {
            Meter m = meterMapper.meterDtoToMeter(meterDto);

            if (idMeter != null) {
                if (meterRepository.existsById(idMeter)) {
                    m.setId(idMeter);
                } else {
                    throw new RuntimeException("Данного счетчика не существует: " + idMeter);
                }
            }

            m.setAccount(accountService.getAccountById(meterDto.getAccountId()));
            m.setType(meterTypeRepository.findByDescription(meterDto.getTypeDescription())
                    .orElseThrow(() -> new RuntimeException("Данные счетчика не коректны: Тип счетчика не найден")));
            m.setTariff(tariffRepository.findByDescription(meterDto.getTariffDescription())
                    .orElseThrow(() -> new RuntimeException("Данные счетчика не коректны: Тариф счетчика не найден")));
            return meterMapper.meterToMeterDto(meterRepository.save(m));
        } else {
            log.error("Данные счетчика не коректны: Серийный номер");
            throw new RuntimeException("Данные счетчика не коректны: Серийный номер");
//            return null;
        }
    }

    @Override
    public List<MeterDataDto> getAllMeterDataByMeterId(Long id) {
        Meter m = meterRepository.findById(id).orElseThrow(() -> new RuntimeException("not found meter by id: " + id));
        return meterDataRepository.findAllByMeterOrderByCreationDateDesc(m).stream().map(dataMapper::meterDataToMeterDataDto).collect(Collectors.toList());
    }

    //TODO реализовать получение показаний для счетчика (поискать лучшее вариант: сделать за "одно" обращение к бд)
    @Override
    public Optional<MeterData> getPreviousMeterDataByMeter(Meter meter) {
        if (meterDataRepository.findCurrentMeterData(meter).isPresent()) {
          MeterData md = meterDataRepository.findCurrentMeterData(meter).get();
          return meterDataRepository.findFirstByMeterAndCreationDateBefore(meter, md.getCreationDate());
        }
        return Optional.empty();
    }

    //TODO реализовать получение показаний для списка счетчиков
    @Override
    public List<MeterData> getPreviousMeterDataByMeters(List<Meter> meter) {
        return new ArrayList<>();
    }

    @Override
    public MeterData getCurrentMeterDataByMeter(Meter meter) {
        //todo: что вернуть если ничего нет ? : избавитсья от null
        return meterDataRepository.findCurrentMeterData(meter).orElse(null);
    }

    @Override
    public List<MeterData> getCurrentMeterDataByMeters(@NotEmpty List<Meter> meter) {
        return meter.isEmpty() ? new ArrayList<>() : meterDataRepository.findCurrentMeterData(meter).orElse(new ArrayList<>());
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

    @Transactional
    @Override
    public Integer deleteMeterDataById(Long dataId) {
        return meterDataRepository.deleteMeterDataById(dataId);
    }

    @Override
    public List<MeterTypeDto> getMeterTypes() {
        return typeMapper.meterTypeToMeterTypeDto(meterTypeRepository.findAll());
    }

    @Override
    public List<TariffDto> getMeterTariffs() {
        return tariffMapper.tariffToTariffDto(tariffRepository.findAll());
    }
}
