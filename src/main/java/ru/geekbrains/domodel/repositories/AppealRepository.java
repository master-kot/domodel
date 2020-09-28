package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Appeal;

import java.util.List;

/**
 * Репозиторий обращений
 */
@Repository
public interface AppealRepository  extends JpaRepository<Appeal, Long> {

    List<Appeal> findAllByAuthorUsername(String name);
}
