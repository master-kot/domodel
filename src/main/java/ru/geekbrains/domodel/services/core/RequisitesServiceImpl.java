package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Requisites;
import ru.geekbrains.domodel.entities.BillType;
import ru.geekbrains.domodel.repositories.RequisitesRepository;
import ru.geekbrains.domodel.services.api.RequisitesService;

import java.util.List;

/**
 * Реализация сервиса реквизитов компании
 */
@Service
@RequiredArgsConstructor
public class RequisitesServiceImpl implements RequisitesService {

    // Репозиторий реквизитов
    private final RequisitesRepository requisitesRepository;

    @Override
    public List<Requisites> getAllRequisites() {
        return requisitesRepository.findAll();
    }

    // TODO
    @Override
    public Requisites getRequisitesByBillType(BillType billType) {
        return null;
    }
}
