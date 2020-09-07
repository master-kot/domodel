package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.domodel.entities.Document;

/**
 * Репозиторий документов
 */
public interface DocumentRepository  extends JpaRepository<Document, Integer> {

}
