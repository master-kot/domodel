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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.geekbrains.domodel.entities.ChoiceOption.*;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;
import static ru.geekbrains.domodel.entities.constants.VoteStatus.*;

/**
 * Реализация сервиса голосований
 */
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    // Репозиторий голосований
    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final UserServiceImpl userService;

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
    public TreeMap<String, String> getVotesDtoDetailById(Long id, Authentication authentication) {
        if (hasAuthenticationRoleAdmin(authentication)) {
            Optional<Vote> optionalVote = voteRepository.findById(id);
            TreeMap<String, String> mapVotes = new TreeMap<>();
            for (VoteData voteData :
                    optionalVote.get().getVoteDatas()) {
                mapVotes.put(voteData.getAuthor().getAddress(), voteData.getOption().getOption());
            }
            return mapVotes;
        }
        return new TreeMap<String, String>();
    }

    @Override
    public List<VoteDto> getAllVotesDto(Authentication authentication) {
        if (authentication != null) {
            Stream<VoteDto> voteDtoStream = voteRepository.findAll().stream().map(voteMapper::votesToVoteDto);

            //todo в моем воображении эта строка добавляет каждому голосованию текущий статус
            voteDtoStream.forEach((VoteDto) -> VoteDto.setStatusVotes(getStatusVotesById(VoteDto.getId(), authentication)));

            return voteDtoStream.sorted((o1, o2) -> o2.getEndDate().compareTo(o1.getEndDate())).collect(Collectors.toList());
        } else return new ArrayList<VoteDto>();
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
   //     Stream<VoteData> voteStream = vote.get().getVoteDatas().stream();

        if (!hasAuthenticationRoleUser(authentication) || !getStatusVotesById(id, authentication).equals(STATUS_LET_VOTE)) {
            return null;
        } else {
            VoteData voteData = new VoteData();
            voteData.setId(id);
            if (choice.equals(AGREED)) voteData.setOption(AGREED);
            if (choice.equals(DISAGREED)) voteData.setOption(DISAGREED);
            if (choice.equals(ABSTAINED)) voteData.setOption(ABSTAINED);
            else return null;
            voteData.setAuthor(userService.getUserByUsername(authentication.getName())); //todo это мы точно текущего юзера получаем?
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
     * Проверить статус голосования
     */
    //todo проверить получение статуса "вы проголосовали".
    private String getStatusVotesById(Long id, Authentication authentication) {
        if (authentication == null) return null;

        Optional<Vote> vote = voteRepository.findById(id);
        if (vote.get().getEndDate().isBefore(LocalDate.now())) return STATUS_RESULT;

        if (hasAuthenticationRoleAdmin(authentication)) return STATUS_VOTING_CONTINUES;

        if (getVotesDtoById(id, authentication).getVoteDatas().contains(userService.getByUsername(authentication.getName())))return STATUS_ALREADY_VOTED;//todo проверить голосовал ли юзер

        return STATUS_LET_VOTE;
    }
}
