package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.repositories.VoteRepository;
import ru.geekbrains.domodel.services.api.VoteService;

/**
 * Реализация сервиса голосований
 */
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    // Репозиторий голосований
    private final VoteRepository voteRepository;

    @Override
    public VoteDto getDtoById(Long id, Authentication authentication) {
        return null;
    }
}
