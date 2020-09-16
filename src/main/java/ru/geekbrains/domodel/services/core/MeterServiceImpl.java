package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.dto.MeterDataDto;
import ru.geekbrains.domodel.dto.MeterDto;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.MeterData;
import ru.geekbrains.domodel.mappers.AccountMapper;
import ru.geekbrains.domodel.mappers.MeterDataMapper;
import ru.geekbrains.domodel.mappers.MeterMapper;
import ru.geekbrains.domodel.repositories.MeterDataRepository;
import ru.geekbrains.domodel.repositories.MeterRepository;
import ru.geekbrains.domodel.repositories.MeterTypeRepository;
import ru.geekbrains.domodel.services.api.AccountService;
import ru.geekbrains.domodel.services.api.MeterService;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;

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

    private final MeterRepository meterRepository;
    private final MeterDataRepository meterDataRepository;
    private final MeterTypeRepository meterTypeRepository;

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
//        Account account = accountService.get

        return null;
    }

    @Override
    public List<MeterDto> getAllMeters(Authentication authentication) {
        if (authentication != null) {
            if (hasAuthenticationRoleAdmin(authentication)) {
                List<Meter> meters;
                MeterDto meterDto;
                List<MeterDto> meterDtoList = new ArrayList<>();
                List<MeterData> currentData;

                meters = meterRepository.findAll();
                currentData = getCurrentMeterDataByMeters(meters);

                for(Meter m : meters) {
                    meterDto = meterMapper.meterToMeterDto(m);
                    for(MeterData md : currentData) {
                        if (m.getId().equals(md.getMeter().getId())) {
                            meterDto.setCurrentMeterData(md.getValue());
                            break;
                        }
                    }
                    meterDtoList.add(meterDto);
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
    public Map<AccountDto, List<MeterDto>> getMetersUser(Authentication authentication) {
        if (authentication != null) {
            List<Meter> meters;
            List<MeterData> currentData;
            MeterDto meterDto;
            List<MeterDto> meterDtoList;
            Map<AccountDto, List<MeterDto>> map = new HashMap<>();
            List<Account> accounts = accountService.getAllAccountsByUserUsername(authentication.getName());


            for (Account account : accounts) {
                meterDtoList = new ArrayList<>();
                meters = meterRepository.findByAccount(account);
                currentData = getCurrentMeterDataByMeters(meters);

                for(Meter m : meters) {
                    meterDto = meterMapper.meterToMeterDto(m);
                    for(MeterData md : currentData) {
                        if (m.getId().equals(md.getMeter().getId())) {
                            meterDto.setCurrentMeterData(md.getValue());
                            break;
                        }
                    }
                    meterDtoList.add(meterDto);
                }
                map.put(accountMapper.accountToAccountDto(account), meterDtoList);
            }
            return map;
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
    public MeterDataDto submitMeterData(MeterDataDto meterDataDto, Long meterId) {
        if (meterId.equals(meterDataDto.getMeterId())) {
            if (meterDataDto.getCreationDate() == null) {
                meterDataDto.setCreationDate(LocalDate.now());
            }
            Meter meter = meterRepository.findById(meterDataDto.getMeterId())
                    .orElseThrow(() -> new RuntimeException("Meter not found by id: " + meterDataDto.getMeterId()));

            Optional<MeterData> previous = getCurrentMeterDataByMeter(meter);
            if (previous.isPresent()) {
                if (previous.get().getCreationDate().getMonth().equals(meterDataDto.getCreationDate().getMonth())) {
                    meterDataDto.setId(previous.get().getId());
                }
            }
            MeterData current = dataMapper.meterDataDtoToMeterData(meterDataDto);
            current.setMeter(meter);

            return dataMapper.meterDataToMeterDataDto(meterDataRepository.save(current));
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public MeterDto saveOrUpdate(MeterDto meterDto) {
        Objects.requireNonNull(meterDto, "Данные счетчика не коректны!");

        if (meterDto.getSerialNumber() != null) {
            Meter m = meterMapper.meterDtoToMeter(meterDto);
            m.setAccount(accountService.getAccountById(meterDto.getAccountId()));
            m.setType(meterTypeRepository.findByDescription(meterDto.getTypeDescription()));
            return meterMapper.meterToMeterDto(meterRepository.save(m));
        } else {
//            throw new RuntimeException("Данные счетчика не коректны: ID или Серийный номер");
            log.error("Данные счетчика не коректны: Серийный номер");
            return null;
        }
    }

    @Override
    public List<MeterDataDto> getAllMeterDataByMeterId(Long id) {
        Meter m = meterRepository.findById(id).orElseThrow(() -> new RuntimeException("not found meter by id: " + id));
        return meterDataRepository.findAllByMeterOrderByCreationDateDesc(m).stream().map(data -> MeterDataDto.builder()
                .creationDate(data.getCreationDate())
                .value(data.getValue())
                .meterId(data.getMeter().getId())
                .build())
                .collect(Collectors.toList());
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
    public List<MeterData> getPreviousMeterDatasByMeters(List<Meter> meter) {
        return new ArrayList<>();
    }

    @Override
    public MeterData getCurrentMeterDataByMeter(Meter meter) {
        return meterDataRepository.findCurrentMeterData(meter).orElse(null);
    }

    @Override
    public List<MeterData> getCurrentMeterDataByMeters(@NotEmpty List<Meter> meter) {
        return meter.isEmpty() ? new ArrayList<>() : meterDataRepository.findCurrentMeterData(meter).orElse(new ArrayList<>());
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

    @Transactional
    @Override
    public Integer deleteMeterDataById(Long dataId) {
        return meterDataRepository.deleteMeterDataById(dataId);
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

    /**
     * Проверить, что пользователь имеет роль Админа
     */
    //TODO: made Utils
    private boolean hasAuthenticationRoleAdmin(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_ADMIN)));
    }

    private boolean hasAuthenticationRoleUser(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_USER)));
    }
}
