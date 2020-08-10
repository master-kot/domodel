package ru.geekbrains.domodel.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Requisites;
import ru.geekbrains.domodel.repositories.RequisitesRepository;
import ru.geekbrains.domodel.services.api.RequisitesService;

import java.util.List;

/**
 * Реализация сервиса реквизитов компании
 */
@Service
public class RequisitesServiceImpl implements RequisitesService {

    //
    private final RequisitesRepository requisitesRepository;

    @Autowired
    public RequisitesServiceImpl(RequisitesRepository requisitesRepository) {
        this.requisitesRepository = requisitesRepository;
    }

    @Override
    public List<Requisites> getAllRequisites() {
        return requisitesRepository.findAll();
    }
}
