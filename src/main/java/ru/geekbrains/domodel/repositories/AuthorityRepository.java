package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.User;

/**
 * Репозиторий ролей пользователей
 */
@Repository
public interface AuthorityRepository extends JpaRepository<User, Long> {

}
