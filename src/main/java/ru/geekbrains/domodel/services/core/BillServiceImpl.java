package ru.geekbrains.domodel.services.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Bill;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.repositories.BillRepository;
import ru.geekbrains.domodel.services.api.BillService;
import ru.geekbrains.domodel.services.api.UserService;

import java.util.List;

/**
 * Реализация сервиса счетов (платежных документов)
 */
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final UserService userService;

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
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return null;
        } else {
            return billRepository.findAllByUser(user);
        }
    }

    /*
    * @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long aLong) {
        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }*/
}
