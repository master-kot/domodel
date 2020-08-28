package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.entities.UserRepresentation;
import ru.geekbrains.domodel.repositories.NewsRepository;
import ru.geekbrains.domodel.services.api.NewsService;

import javax.validation.Valid;
import java.security.Principal;

import static ru.geekbrains.domodel.entities.constants.Messages.*;

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
    @GetMapping("news_archive/{id}")
    public String getNewsArchivePage(Model model, Principal principal, Authentication authentication, Pageable pageable, @PathVariable int id) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        if (id == 0) id =1;
        Pageable pageRequest = PageRequest.of(id, 2);
        model.addAttribute("all", newsService.findAll(pageRequest));
        //model.addAttribute("newsArchive", newsService.getNewsArchive(authentication));
        Page<News> page = newsService.findAll(pageRequest);

        model.addAttribute("page", page);
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
        model.addAttribute("news", new News());
        return "news/news_edit";
    }

    @PostMapping("/edit")
    public String addNews(@Valid @ModelAttribute("newsData") News newsData, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("newsData", newsService.saveNews(newsData));
        }
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
