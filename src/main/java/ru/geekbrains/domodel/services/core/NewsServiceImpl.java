package ru.geekbrains.domodel.services.core;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    //получение поcледней новости
    public News getLastNews() {
        List<News> newsList = getAllNews();
        if (!newsList.isEmpty()) {
            return newsList.get(0);
        }
        return null;
    }

    @Override
    //получаем отсортированный по дате (от свежих к старым) список всех новостей
    public List<News> getAllNews() {
        List<News> list = newsRepository.findAll();
        Collections.reverse(list);
        return list;
    }

    @Override
    //получаем новость по id
    public News getNewsById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        return news.orElse(null);
    }

    @Override
    //Архив новостей
    public List<News> getNewsArchive(org.springframework.security.core.Authentication authentication) {
        List<News> newsArchive = new ArrayList<>();
        if (authentication.getName().equals("admin")) newsArchive = getAllNews();
        else newsArchive = getAllVisibleNews();
        return newsArchive;
    }

    @Override
    // Новости на главную страницу
    public List<News> getRelevantNews(org.springframework.security.core.Authentication authentication) {
        List<News> newsRelevant = new ArrayList<>();
        if (authentication == null) newsRelevant = getPublicNews();
        else newsRelevant = getAllVisibleNews();
        if (newsRelevant.size()>10) newsRelevant.subList(0,9);
        return newsRelevant;
    }


    @Override
    public List<News> getAllVisibleNews() {
        //список новостей актуальных новостей для зарегистрированных пользователей. Сначала закрепленные, потом остальные по дате от новых к старым
        List<News> allNewsList = getAllNews();
        List<News> pinnedNewsList = getPinnedNews();
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

    @Override
    public List<News> getPinnedNews() {
        //возвращает лист из 2 закрепленных новостей
        List<News> allNewsList = getAllNews();
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

    @Override
    public List<News> getPublicNews() {
        //список новостей (10 штук) для незарегистрированных пользователей. Сначала закрепленные, потом остальные по дате от новых к старым
        List<News> allNewsList = getAllNews();
        List<News> pinnedNewsList = getPinnedNews();
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

    // РЕДАКТИРОВАНИЕ
    @Override
    public void deleteNewsById(Long id) {
        //удаляем новость, меняя ее видимость и удаляя ее из закрепленных
        getNewsById(id).setVisible(false);
        getNewsById(id).setPinned(false);
    }


    @Override
    public News createNews(String title,
                           String fullText,
                           boolean hidden,
                           boolean pinned,
                           String pictureLink,
                           User user) {

        News newNews = new News(title, fullText, hidden, pinned, pictureLink, user);
        return newsRepository.save(newNews);
    }

    // TODO через POST   сооздающиеся автоматически (дата, автор, visible); учесть, что закрепленных может быть только 2
    @Override
    public News changeNews(Long id,
                           String title,
                           String fullText,
                           boolean hidden,
                           boolean pinned,
                           boolean visible, //можно через редактирование восстановить удаленную новость
                           String pictureLink) {
        News news = newsRepository.getOne(id);
        if (news != null) {
            news.setTitle(title);
            news.setFullText(fullText);
            news.setHidden(hidden);
            news.setPinned(pinned);
            news.setVisible(visible);
            news.setPictureLink(pictureLink);
            return newsRepository.save(news);
        }
        return null;
    }


    @Override
    public void toPinnedNews(News news) {
        //Закрепляем выбранную новость,если закрепленных уже 2, то одну открепляем
        List<News> pinnedNews = getPinnedNews();
        if (pinnedNews.size() > 1) pinnedNews.get(1).setPinned(false);
        news.setPinned(true);
    }


}