package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.News;

/**
 * Репозиторий новостей
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
