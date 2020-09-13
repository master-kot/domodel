package ru.geekbrains.domodel.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.dto.VoteRequest;
import ru.geekbrains.domodel.entities.Vote;
import ru.geekbrains.domodel.entities.VoteData;
import ru.geekbrains.domodel.mappers.VoteMapper;
import ru.geekbrains.domodel.repositories.VoteRepository;
import ru.geekbrains.domodel.services.api.VoteService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.geekbrains.domodel.entities.ChoiceOption.*;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;

/**
 * Реализация сервиса голосований
 */
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    // Репозиторий голосований
    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;

    @Override
    public VoteDto getVotesDtoById(Long id, Authentication authentication) {
        if (authentication != null) {
            Optional<Vote> optionalVote = voteRepository.findById(id);
            optionalVote.map(voteMapper::votesToVoteDto).orElse(null).setStatusVotes(getStatusVotesById(id, authentication));
            return optionalVote.map(voteMapper::votesToVoteDto).orElse(null);
        }
        return null;
    }

    @Override
    public Map<String, String> getVotesDtoDetailById(Long id, Authentication authentication) {
        if (hasAuthenticationRoleAdmin(authentication)) {
            Optional<Vote> optionalVote = voteRepository.findById(id);
            Map<String, String> mapVotes = new HashMap<>();
            for (VoteData voteData :
                    optionalVote.get().getVoteDatas()) {
                mapVotes.put(voteData.getAuthor().getAddress(), voteData.getOption().getOption());
            }
            return mapVotes;
        }
        return null;
    }

    @Override //todo добавить статусы голосований
    public List<VoteDto> getAllVotesDto(Authentication authentication) {
        if (authentication != null) {
            Stream<VoteDto> voteDtoStream = voteRepository.findAll().stream().map(voteMapper::votesToVoteDto);
            return voteDtoStream.sorted((o1, o2) -> o2.getEndDate().compareTo(o1.getEndDate())).collect(Collectors.toList());
        } else return null;
    }

    @Override
    public VoteDto save(VoteRequest voteRequest, Authentication authentication) {
        if (hasAuthenticationRoleAdmin(authentication)) {
            Vote vote = voteMapper.voteRequestToVote(voteRequest);
            return voteMapper.votesToVoteDto(voteRepository.save(vote));
        } else return null;
    }

    @Override
    public VoteDto updateVoteDtoById(Long id, Authentication authentication, String choice) {
        Optional<Vote> vote = voteRepository.findById(id);
        Stream<VoteData> voteStream = vote.get().getVoteDatas().stream();

        if (!hasAuthenticationRoleUser(authentication) || !getStatusVotesById(id, authentication).equals("Проголосовать")) { //todo сделать не так топорно
            return null;
        } else {
            VoteData voteData = new VoteData();
            voteData.setId(id);
            if (choice.equals(AGREED)) voteData.setOption(AGREED);
            if (choice.equals(DISAGREED)) voteData.setOption(DISAGREED);
            if (choice.equals(ABSTAINED)) voteData.setOption(ABSTAINED);
            else return null;
            voteData.setAuthor(юзер); //todo сюда пихнуть юзера
            vote.get().getVoteDatas().add(voteData);
            voteRepository.save(vote.get());
            return voteMapper.votesToVoteDto(vote.get());
        }
    }

    /**
     * Проверить, что пользователь имеет роль Админа
     */
    private boolean hasAuthenticationRoleAdmin(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_ADMIN)));
    }

    /**
     * Проверить, что пользователь имеет роль Юзера
     */
    private boolean hasAuthenticationRoleUser(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_USER)));
    }

    /**
     * Проверить, что пользователь имеет роль Юзера
     */
    //todo сделать проверку статуса "вы проголосовали".
    // возможно добавить сущность voteStatusOption
    private String getStatusVotesById(Long id, Authentication authentication) {
        if (authentication == null) return null;

        Optional<Vote> vote = voteRepository.findById(id);
        if (vote.get().getEndDate().isBefore(LocalDate.now())) return "Результаты";

        if (hasAuthenticationRoleAdmin(authentication)) return "Сбор голосов";

        if (здеесь проверить голосовал ли юзер)return "Вы проголосовали";//todo проверить голосовал ли юзер

        return "Проголосовать";

    }
}
