package ru.geekbrains.domodel.services.core;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.repositories.NewsRepository;
import ru.geekbrains.domodel.services.api.NewsService;

import java.util.*;

/**
 * Реализация сервиса новостей
 */
@Service
public class NewsServiceImpl implements NewsService {

    // Репозиторий новостей
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    //ЧТЕНИЕ

    @Override
    //получаем новость по id
    public News readNewsById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        return news.orElse(null);
    }

    //todo сделать пагинацию
    @Override
    //Архив новостей
    public List<News> readNewsArchive(org.springframework.security.core.Authentication authentication) {
//        List<News> newsArchive = getAllVisibleNews();
//        if (authentication.getName().equals("admin")) newsArchive.addAll(getDeletedNews());
//        return newsArchive;
        PageRequest pageable = new PageRequest(1, 2);
        List<News> newsArchive = new ArrayList<>();
        Page<News> page = newsRepository.findAll(pageable);
        if (authentication.getName().equals("admin")) newsArchive.addAll(readDeletedNews());
        for (int i = 0; i <= page.getTotalPages(); i++) {
            List<News> listPage = newsRepository.findAll(pageable.next()).getContent();

            newsArchive.addAll(listPage);
        }
        return newsArchive;
    }

    @Override
    // Новости на главную страницу
    public List<News> readRelevantNews(org.springframework.security.core.Authentication authentication) {
        List<News> newsRelevant = new ArrayList<>();
        if (authentication == null) newsRelevant = readPublicNews();
        else newsRelevant = readAllVisibleNews();
        if (newsRelevant.size() > 10) newsRelevant.subList(0, 9);
        return newsRelevant;
    }

    // РЕДАКТИРОВАНИЕ

    //todo сделать метод создание новости по параметрам с фронта
    @Override
    public News createNews(NewsDto newsDto) {
        // News newNews = new News(title, fullText, hidden, pinned, pictureLink, user);
        //return newsRepository.save(newNews);
        return null;
    }


    // РЕДАКТИРОВАНИЕ

    @Override
    public News updateVisibilityNewsById(Long id, boolean visible) {
        //удаляем новость, меняя ее видимость и удаляя ее из закрепленных
        readNewsById(id).setVisible(false);
        readNewsById(id).setPinned(false);
        return null;
    }

    // TODO через POST сделать метод изменения новости; учесть, что закрепленных может быть только 2
    @Override
    public News updateNewsById(Long id, NewsDto newsDto) {
        News news = newsRepository.getOne(id);
//        if (news != null) {
//            news.setTitle(title);
//            news.setFullText(fullText);
//            news.setHidden(hidden);
//            news.setPinned(pinned);
//            news.setVisible(visible);
//            news.setPictureLink(pictureLink);
//            return newsRepository.save(news);
//        }
        return null;
    }

    @Override
    public News updatePinningNewsById(Long id, boolean pinned) {
        //Закрепляем выбранную новость,если закрепленных уже 2, то одну открепляем
        List<News> pinnedNews = readPinnedNews();
        if (pinnedNews.size() > 1) pinnedNews.get(1).setPinned(false);
        readNewsById(id).setPinned(pinned);
        return null;
    }



    //ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ
    //ЧТЕНИЕ

    //сохранение новости
    public News saveNews(News newNews) {
        return newsRepository.save(newNews);
    }


    //получение поcледней новости
    public News readLastNews() {
        List<News> newsList = readAllNews();
        if (!newsList.isEmpty()) {
            return newsList.get(0);
        }
        return null;
    }

    //получаем отсортированный по дате (от свежих к старым) список всех новостей
    public List<News> readAllNews() {
        List<News> list = newsRepository.findAll();
        Collections.reverse(list);
        return list;
    }

    private Page<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }


    //РЕДАКТИРОВАНИЕ
    private List<News> readAllVisibleNews() {
        //список неудаленных новостей для зарегистрированных пользователей. Сначала закрепленные, потом остальные по дате от новых к старым
        List<News> allNewsList = readAllNews();
        List<News> pinnedNewsList = readPinnedNews();
        List<News> newsList = new ArrayList<News>();
        for (int i = 0; i < pinnedNewsList.size(); i++) { //добавляем закрпленные
            newsList.add(pinnedNewsList.get(i));
        }

        for (int i = 0; i < allNewsList.size(); i++) { //добавляем остальные неудаленные новости
            if (allNewsList.get(i).isVisible() && !allNewsList.get(i).isPinned()) {
                newsList.add(allNewsList.get(i));
            }
        }
        return newsList;
    }

    private List<News> readDeletedNews() {
        //список удаленных новостей
        List<News> allNewsList = readAllNews();
        List<News> newsList = new ArrayList<News>();
        for (int i = 0; i < allNewsList.size(); i++) {
            if (!allNewsList.get(i).isVisible()) {
                newsList.add(allNewsList.get(i));
            }
        }
        return newsList;
    }

    private List<News> readPinnedNews() {
        //возвращает лист из 2 закрепленных новостей
        List<News> allNewsList = readAllNews();
        List<News> newsList = new ArrayList<>();
        byte counter = 0;
        for (int i = 0; i < allNewsList.size(); i++) {
            if (allNewsList.get(i).isPinned()) {
                newsList.add(allNewsList.get(i));
                counter++;
            }
            if (counter == 2) break;
        }
        return newsList;
    }

    private List<News> readPublicNews() {
        //список новостей (10 штук) для незарегистрированных пользователей. Сначала закрепленные, потом остальные по дате от новых к старым
        List<News> allNewsList = readAllNews();
        List<News> pinnedNewsList = readPinnedNews();
        List<News> newsList = new ArrayList<>();
        byte counter = 0;
        for (int i = 0; i < pinnedNewsList.size(); i++) { //добавляем закрепленные, если они публичные
            if (pinnedNewsList.get(i).isHidden()) {
                newsList.add(pinnedNewsList.get(i));
                counter++;
            }
        }
        for (int i = 0; i < allNewsList.size(); i++) { //добавляем обычные новости до 10, если они видимы, публичны и незакреплены
            if (allNewsList.get(i).isHidden() && allNewsList.get(i).isVisible() && !allNewsList.get(i).isPinned()) {
                newsList.add(allNewsList.get(i));
                counter++;
            }
            if (counter == 10) break;
        }
        return newsList;
    }

}