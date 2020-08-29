package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.Vote;

import java.util.Optional;

/**
 * Интерфейс сервиса голосований
 */
public interface VoteService {

    /**
     * Получить голосование по индексу
     */
    Optional<Vote> getById(Long id);
}
