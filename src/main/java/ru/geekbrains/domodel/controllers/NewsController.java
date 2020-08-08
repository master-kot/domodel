package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.services.api.NewsService;

import java.util.List;

/**
 * Контроллер модуля новостей
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    // Сервис новостей
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * Перехват запроса списка новостей
     */
    @GetMapping("")
    public String newsPage(Model model) {
        model.addAttribute("news", newsService.getAllNews());
        return "news";
    }

}
