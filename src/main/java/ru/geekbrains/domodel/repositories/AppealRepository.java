package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Appeal;

import java.util.Collection;

/**
 * Репозиторий обращений
 */
@Repository
public interface AppealRepository  extends JpaRepository<Appeal, Long> {

    Collection<Appeal> findAllByAuthorIdUsername(String name);
}
