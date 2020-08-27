package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.services.api.NewsService;

import java.security.Principal;

/**
 * Контроллер новостей
 */
//todo по идее это страница архива новостей. Прикрутить пагинацию, в зависимости от типа юзера выдавать разные списки
    //todo сделать страницу редакции отдельной новости
@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    // Сервис новостей
    private final NewsService newsService;

    /**
     * Перехват запроса архива новостей
     */
    @GetMapping("")
    public String getNewsArchivePage(Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("newsArchive", newsService.getNewsArchive(authentication));
        return "news/news_archive";
    }

    /**
     * Перехват запроса страницы редактирования новостей
     */
    @GetMapping("/edit")
    public String getNewsEditPage(Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("news", newsService.getAllNews());
        return "news/news_edit";
    }

    @PostMapping("/edit")
    public String addNews(News news) {
        newsService.saveNews(news);
        return "news/news_edit";
    }


    /**
     * Перехват запроса страницы 1 новости
     */
    @GetMapping("/news_details/{id}")
    public String getSingleNewsPage(Model model, Principal principal, @PathVariable Long id) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("newsById", newsService.getNewsById(id));
        return "news/news_details";
    }

}
