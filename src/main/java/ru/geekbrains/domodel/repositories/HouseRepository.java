package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.House;

/**
 * Репозиторий домов
 */
@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {

}
