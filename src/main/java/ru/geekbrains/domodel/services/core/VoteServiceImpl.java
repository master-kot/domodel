package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.mappers.VoteMapper;
import ru.geekbrains.domodel.repositories.VoteRepository;
import ru.geekbrains.domodel.services.api.VoteService;

import java.util.List;

/**
 * Реализация сервиса голосований
 */
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    // Репозиторий голосований
    private final VoteRepository voteRepository;

    // Маппер
    private final VoteMapper voteMapper;

    @Override
    public VoteDto getDtoById(Long id, Authentication authentication) {
        return voteRepository.findById(id).map(voteMapper::voteToVoteDto).orElse(null);
    }

    @Override
    public List<VoteDto> getAllDto(Authentication authentication) {
        return voteMapper.voteToVoteDto(voteRepository.findAll());
    }
}
