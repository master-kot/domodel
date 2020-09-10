package ru.geekbrains.domodel.services.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.dto.BillDto;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Bill;
import ru.geekbrains.domodel.repositories.BillRepository;
import ru.geekbrains.domodel.services.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация сервиса счетов (платежных документов)
 */
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final UserService userService;
    private final AccountService accountService;
    private final TariffService tariffService;
    private final MeterService meterService;
    private final RequisitesService requisitesService;

    @Override
    public List<BillDto> getAllDto() {
        return null; //billRepository.findAll();
    }

    @Override
    public BillDto getDtoById(@NonNull Long billId) {
        return null; //billRepository.findById(billId).orElse(null);
    }

    @Override
    public List<BillDto> getAllDtoByUserUsername(@NonNull String username) {
        List<Account> user = accountService.getAllAccountsByUserUsername(username);
        if (user == null) {
            return null;
        } else {
            return null; //billRepository.findAllByAccountId(user.getAccountIds()); // заглушка
        }
    }

    @Override
    public BillDto save(BillDto bill) {
        //  прикрепляем номер счета (либо делаем это при создании счета) - номер счета это id bill
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
//                    meterDataCurrent.setValue(meter.getTariff().getDefaultIncreaseValue());
                    meterDataCurrent.setMeter(meter);
//                    calculation.setCalculated(false);
                }
//                else calculation.setCalculated(true);
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
        return null; //billRepository.findAllByAccount(account);
    }

    @Override
    public Bill update(BillDto bill) {
        return null;
    }
}
