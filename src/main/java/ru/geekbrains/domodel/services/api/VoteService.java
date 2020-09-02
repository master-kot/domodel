package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.entities.Vote;

import java.util.Optional;

/**
 * Интерфейс сервиса голосований
 */
public interface VoteService {

    /**
     * Получить голосование по индексу
     */
    VoteDto getVoteById(Long id);
}
