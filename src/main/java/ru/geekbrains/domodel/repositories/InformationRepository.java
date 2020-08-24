package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Information;

import java.util.Optional;

/**
 * Репозиторий информации о компании
 */
@Repository
public interface InformationRepository extends JpaRepository<Information, Integer> {

    Optional<Information> findById(Integer id);
}
