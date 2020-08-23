package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Person;

import java.util.Optional;

/**
 * Репозиторий должностного лица компании
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    /**
     *
     */
    Optional<Person> findById(Integer id);
}
