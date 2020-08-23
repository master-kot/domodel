package ru.geekbrains.domodel.services.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.*;
import ru.geekbrains.domodel.entities.constants.BillType;
import ru.geekbrains.domodel.repositories.BillRepository;
import ru.geekbrains.domodel.services.api.*;

import java.util.ArrayList;
import java.util.Date;
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
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill save(@NonNull Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill findById(@NonNull Long billId) {
        return billRepository.findById(billId).orElse(null);
    }

    @Override
    public List<Bill> findAllByUsername(@NonNull String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return null;
        } else {
            return billRepository.findAllByAccount((Account) user.getAccounts().toArray()[0]); // заглушка
        }
    }

    @Override
    public Bill createBillByAccount(Account account) {
        //  прикрепляем номер счета (либо делаем это при создании счета) - номер счета это id bill
        Bill bill = new Bill();
        bill.setAccount(account);
        bill.setCreationDate(new Date());
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
                if ((new Date().getTime() - meterDataCurrent.getCreationDate().getTime()) / (24 * 60 * 60 * 1000) > 30) {
                    meterDataCurrent = new MeterData();
                    meterDataCurrent.setCreationDate(new Date());
//                    meterDataCurrent.setValue(meter.getTariff().getDefaultIncreaseValue());
                    meterDataCurrent.setMeter(meter);
//                    calculation.setCalculated(false);
                }
//                else calculation.setCalculated(true);
                calculation.setPreviousData(meterDataPrev);
                calculation.setCurrentData(meterDataCurrent);
                calculation.setAmount(meterDataCurrent.getValue() - meterDataPrev.getValue());
                calculation.setPrice(meter.getTariff().getPrice());
                calculation.setBill(bill);
                calculation.setCost(calculation.getAmount() * meter.getTariff().getPrice());
                bill.getCalculations().add(calculation);
            }
        }
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> createAll() {
        List<Bill> bills = new ArrayList<>();
        accountService.getAllAccounts().forEach(this::createBillByAccount);
        return bills;
    }

    @Override
    public List<Bill> getAllByAccount(Account account) {
        return billRepository.findAllByAccount(account);
    }

    @Override
    public Bill changeById(Long Id, Bill billData) {
        return null;
    }

    public List<Bill> getAllBillsByAccount(Account account) {
        return billRepository.findAllByAccount(account);
    }
}
