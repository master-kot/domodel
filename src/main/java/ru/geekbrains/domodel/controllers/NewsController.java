package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String getNewsArchivePage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("news", newsService.getAllNews());
        return "news/news_archive";
    }

    /**
     * Перехват запроса страницы редактирования новостей
     */
    @GetMapping("/edit")
    public String getNewsEditPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("news", newsService.getAllNews());
        return "news/news_edit";
    }
}
