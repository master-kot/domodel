package ru.geekbrains.domodel.services.core;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.mappers.NewsMapper;
import ru.geekbrains.domodel.repositories.NewsRepository;
import ru.geekbrains.domodel.services.api.NewsService;

import java.time.LocalDate;
import java.util.*;

/**
 * Реализация сервиса новостей
 */
@Service
public class NewsServiceImpl implements NewsService {

    // Репозиторий новостей
    private final NewsRepository newsRepository;
    private  NewsMapper newsMapper;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }


    //ЧТЕНИЕ

    @Override
    //получаем новость по id
    public NewsDto getNewsById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        NewsDto newsDto = newsMapper.newsToNewsDto(news.get());
        return newsDto;
    }

    //todo сделать пагинацию, добавить секьюрити
    @Override
    //Архив новостей
    public List<NewsDto> getNewsArchive() {
//        List<News> newsArchive = getAllVisibleNews();
//        if (authentication.getName().equals("admin")) newsArchive.addAll(getDeletedNews());
//        return newsArchive;
//        PageRequest pageable = new PageRequest(1, 2);
//        List<News> newsArchive = new ArrayList<>();
//        Page<News> page = newsRepository.findAll(pageable);
//        if (authentication.getName().equals("admin")) newsArchive.addAll(readDeletedNews());
//        for (int i = 0; i <= page.getTotalPages(); i++) {
//            List<News> listPage = newsRepository.findAll(pageable.next()).getContent();
//            newsArchive.addAll(listPage);
//        }
        List<News> newsArchive = getAllVisibleNews();
        List<NewsDto> newsDtoList = new ArrayList<>();
        if(newsArchive.size()>10) {
        for (int i = 0; i < newsArchive.size(); i++) {
            newsDtoList.add(newsMapper.newsToNewsDto(newsArchive.get(i)));
        }
        }
        return newsDtoList;
    }

    @Override
    // Новости на главную страницу
    //todo добавить секьюрити
    public List<NewsDto> getAllRelevantNews() {
        List<NewsDto> newsRelevant = new ArrayList<>();
        List<News> allNews = getAllNews();
        //if (authentication == null) newsRelevant = readPublicNews();
        //else newsRelevant = readAllVisibleNews();
        //if (newsRelevant.size() > 10) newsRelevant.subList(0, 9);
        for (int i = 0; i < 10; i++) {
            newsRelevant.add(newsMapper.newsToNewsDto(allNews.get(i)));
        }
        return newsRelevant;
    }

    // РЕДАКТИРОВАНИЕ

    @Override
    //метод создания новости
    public NewsDto createNews(NewsDto newsDto) {
        News newNews =newsMapper.newsDtoToNews(newsDto);
        newsRepository.save(newNews);
        return newsDto;
    }


    // РЕДАКТИРОВАНИЕ

    @Override
    public NewsDto updateVisibilityNewsById(Long id, boolean visible) {
        //удаляем новость, меняя ее видимость и удаляя ее из закрепленных
        getNewsById(id).setVisible(false);
        getNewsById(id).setPinned(false);
        NewsDto newsDto = newsMapper.newsToNewsDto(newsRepository.getOne(id));
        return newsDto;
    }

    // TODO учесть, что закрепленных может быть только 2
    @Override
    public NewsDto updateNewsById(Long id, NewsDto newsDto) {
        News news = newsRepository.getOne(id);
        news.setCreationDate(LocalDate.now());
        news.setTitle(newsDto.getTitle());
        news.setFullText(newsDto.getFullText());
        news.setPictureLink(newsDto.getPictureLink());
        news.setHidden(newsDto.isHidden());
        news.setPinned(newsDto.isPinned());
        news.setVisible(newsDto.isVisible());
        NewsDto newNewsDto = getNewsById(id);
        return newNewsDto;
    }

    @Override
    public NewsDto updatePinningNewsById(Long id, boolean pinned) {
        //Закрепляем выбранную новость,если закрепленных уже 2, то одну открепляем
        List<News> pinnedNews = getPinnedNews();
        if (pinnedNews.size() > 1) pinnedNews.get(1).setPinned(false);
        getNewsById(id).setPinned(pinned);
        NewsDto newsDto = newsMapper.newsToNewsDto(newsRepository.getOne(id));
        return newsDto;
    }


    //ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ
    //ЧТЕНИЕ

    //сохранение новости
    public News saveNews(News newNews) {
        return newsRepository.save(newNews);
    }

    public NewsDto saveNews(NewsDto newNews) {
        News news = newsMapper.newsDtoToNews(newNews);
        newsRepository.save(news);
        return newNews;
    }

    //получение поcледней новости
    public News getLastNews() {
        List<News> newsList = getAllNews();
        if (!newsList.isEmpty()) {
            return newsList.get(0);
        }
        return null;
    }

    //получаем отсортированный по дате (от свежих к старым) список всех новостей
    public List<News> getAllNews() {
        List<News> list = newsRepository.findAll();
        Collections.reverse(list);
        return list;
    }

    private Page<News> getAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }


    //РЕДАКТИРОВАНИЕ

//    public News(NewsDto newsDto) {
//        this.creationDate = newsDto.getCreationDate();
//        this.title = newsDto.getTitle();
//        this.fullText = newsDto.getFullText();
//        this.pictureLink = newsDto.getPictureLink();
//        this.hidden = newsDto.isHidden();
//        this.pinned = newsDto.isPinned();
//        this.visible = newsDto.isVisible();
//        User user = userService.getUserByUsername(newsDto.getUserName());
//        this.authorId = user;
//    }

    private List<News> getAllVisibleNews() {
        //список неудаленных новостей для зарегистрированных пользователей. Сначала закрепленные, потом остальные по дате от новых к старым
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

    private List<News> getDeletedNews() {
        //список удаленных новостей
        List<News> allNewsList = getAllNews();
        List<News> newsList = new ArrayList<News>();
        for (int i = 0; i < allNewsList.size(); i++) {
            if (!allNewsList.get(i).isVisible()) {
                newsList.add(allNewsList.get(i));
            }
        }
        return newsList;
    }

    private List<News> getPinnedNews() {
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

    private List<News> getPublicNews() {
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

}