package ru.geekbrains.domodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Meter;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий счетчиков показаний
 */
@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {

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

    /**
     * Получить список счетчиков аккаунта
     */
    List<Meter> findByAccount(Account account);

    /**
     * Найти счетчик по его серийному номеру
     */
    Optional<Meter> findBySerialNumber(Integer serialNumber);

    /**
     *
     */
    Optional<Meter> findById(Long id);

    Optional<List<Meter>> findAllByAccountIn(List<Account> accounts);

    Integer deleteMeterById(Long id);
}
