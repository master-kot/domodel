package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.PhotoLink;

/**
 * Репозиторий фотографий
 */
@Repository
public interface PhotoRepository extends JpaRepository<PhotoLink, Integer> {

}
