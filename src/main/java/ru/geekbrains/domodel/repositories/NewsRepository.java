package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.News;

/**
 * Репозиторий новостей
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    /*
     * СОГЛАШЕНИЕ О НАИМЕНОВАНИИ МЕТОДОВ РЕПОЗИТОРИЕВ
     * Optional<Object> findById(Long id) найти объект по параметру
     * List<Object> findAll() найти все объекты
     * List<Object> findAllByUser(User user) найти все объекты по параметру
     * void delete(Object object) удалить конкретный объект
     * Long deleteById(Long id) удалить объект по параметру
     * void deleteAll(List<Object> objects) удалить список объектов
     * Object save(Object object) сохранить объект
     * List<Object> saveAll(List<Object> objects) сохранить список объектов
     */
}
