package ru.geekbrains.domodel.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Bill;
import ru.geekbrains.domodel.repositories.BillRepository;
import ru.geekbrains.domodel.services.api.BillService;

import java.util.List;

/**
 * Реализация сервиса счетов (платежных документов)
 */
@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
}
