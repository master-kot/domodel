package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Requisites;

/**
 * Репозиторий реквизитов компании
 */
@Repository
public interface RequisitesRepository extends JpaRepository<Requisites, Integer> {

}
