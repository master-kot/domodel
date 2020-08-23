package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Person;

import java.util.Optional;

/**
 * Интерфейс сервиса должностных лиц компании
 */
public interface PersonService {

    /**
     * Получить должностного лица компании по его номеру
     */
    Optional<Person> getById(Integer id);
}
