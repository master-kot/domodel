package ru.geekbrains.domodel.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.users.entities.User;

import java.util.Optional;

/**
 * Репозиторий пользователей
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Найти пользователя по его имени
     */
    Optional<User> findByUsername(String username);

}