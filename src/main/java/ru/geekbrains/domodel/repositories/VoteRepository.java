package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Vote;

/**
 * Репозиторий голосований
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
