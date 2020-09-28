package ru.geekbrains.domodel.services.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.dto.BillDto;
import ru.geekbrains.domodel.entities.Bill;
import ru.geekbrains.domodel.mappers.BillMapper;
import ru.geekbrains.domodel.repositories.BillRepository;
import ru.geekbrains.domodel.services.api.BillService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса счетов (платежных документов)
 */
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillMapper billMapper;


    @Override
    public List<BillDto> getAllDto() {
        return billRepository.findAll().stream()
                .map(billMapper::billToBillDto).collect(Collectors.toList());
    }

    @Override
    public List<BillDto> getAllUnpaidDtoByAccounts(List<AccountDto> accountDtos) {
        List<Long> longList = accountDtos.stream().map(AccountDto::getId).collect(Collectors.toList());
        return billMapper.billToBillDto(billRepository.findAllByAccountId(longList));
    }

    @Override
    public List<BillDto> getAllDtoByAccounts(List<AccountDto> accountDtos) {
        List<Long> longList = accountDtos.stream().map(AccountDto::getId).collect(Collectors.toList());
        return billMapper.billToBillDto(billRepository.findAllByAccountId(longList));
    }

    @Override
    public BillDto getDtoById(@NonNull Long id) {
        return billRepository.findById(id).map(billMapper::billToBillDto).orElse(null);
    }

    @Override
    public BillDto save(BillDto bill) {
        /*Bill bill = new Bill();
        bill.setAccount(account);
        bill.setCreationDate(LocalDate.now());
        bill.setTarget("Заглушка target");
        // TODO читаем алгоритм формирования счета
        BillType billType = BillType.METERS;
        bill.setRequisites(requisitesService.getRequisitesByBillType(billType));

        List<Meter> meterList = meterService.getAllMetersByAccount(account);
        for (Meter meter : meterList) {
            Calculation calculation = new Calculation();
            List<MeterData> meterDataList = meter.getMeterDatas();
            if (meterDataList.size() > 2) {
                MeterData meterDataPrev = meterDataList.get(meterDataList.size() - 2);
                MeterData meterDataCurrent = meterDataList.get(meterDataList.size() - 1);
                // TODO пока расчет платежа если показания не поданы можно закомментировать.
                // Логика автоматического рассчета текущих показаний на основе средних значений defaultIncreaseValue,
                // указанных в сущности Tariff для случаев, когда показания не подавались больше 30 дней,
                // будет реализована В MVP 1!!!
                if (LocalDate.now().getMonth().compareTo(meterDataCurrent.getCreationDate().getMonth()) < 1) {
                    meterDataCurrent = new MeterData();
                    meterDataCurrent.setCreationDate(LocalDate.now());
                    meterDataCurrent.setValue(meter.getTariff().getDefaultIncreaseValue());
                    meterDataCurrent.setMeter(meter);
                    calculation.setCalculated(false);
                }
                else calculation.setCalculated(true);
                calculation.setPreviousData(meterDataPrev);
                calculation.setCurrentData(meterDataCurrent);
                calculation.setAmount(meterDataCurrent.getValue() - meterDataPrev.getValue());
                calculation.setPrice(meter.getType().getTariff().getPrice());
                calculation.setBill(bill);
                calculation.setCost(calculation.getAmount() * meter.getType().getTariff().getPrice());
                bill.getCalculations().add(calculation);
            }
        }*/
        return null; //billRepository.save(bill);
    }

    @Override
    public List<BillDto> saveAll() {
        List<BillDto> bills = new ArrayList<>();
//        accountService.getAllAccounts().forEach(this::createBillByAccount);
        return bills;
    }

    @Override
    public List<BillDto> getAllDtoByAccount(AccountDto account) {
        return billRepository.findAllByAccountId(account.getId())
                .stream().map(billMapper::billToBillDto).collect(Collectors.toList());
    }

    @Override
    public Bill update(BillDto bill) {
        return null;
    }


}
