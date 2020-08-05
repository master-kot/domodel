package ru.geekbrains.domodel.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.news.entities.News;
import ru.geekbrains.domodel.news.services.api.NewsService;

import java.util.List;

/**
 * Контроллер модуля новостей
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    // Сервис товаров
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * Отобразить весь список продуктов магазина
     */
    @GetMapping("")
    public String shopPage(Model model) {
        List<News> allProducts = newsService.getAllNews();
        model.addAttribute("products", allProducts);
        return "news";
    }

}
