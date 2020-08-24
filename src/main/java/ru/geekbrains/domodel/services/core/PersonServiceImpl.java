package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.entities.Person;
import ru.geekbrains.domodel.repositories.PersonRepository;
import ru.geekbrains.domodel.services.api.PersonService;

import java.util.Optional;

/**
 * Реализация сервиса должностных лиц компании
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    // Репозиторий должностных лиц
    private  final PersonRepository personRepository;

    @Override
    public Optional<Person> getById(Integer id) {
        return personRepository.findById(id);
    }
}
