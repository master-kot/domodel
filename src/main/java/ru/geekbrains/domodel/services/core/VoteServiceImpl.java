package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.entities.Vote;
import ru.geekbrains.domodel.repositories.VoteRepository;
import ru.geekbrains.domodel.services.api.VoteService;

import java.util.Optional;

/**
 * Реализация сервиса голосований
 */
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    // Репозиторий голосований
    private final VoteRepository voteRepository;

    @Override
    public VoteDto getVoteById(Long id) {
        return null;
    }
}
