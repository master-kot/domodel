package ru.geekbrains.domodel.services.api;

import ru.geekbrains.domodel.entities.PhotoLink;

import java.util.List;

/**
 * Интерфейс сервиса фотографий
 */
public interface PhotoService {

    /**
     * Созранить все ссылки на фотографии
     *
     * @param photoLinks список адресов фотографий
     * @return список ссылок
     */
    List<PhotoLink> saveAll(List<String> photoLinks);
}
