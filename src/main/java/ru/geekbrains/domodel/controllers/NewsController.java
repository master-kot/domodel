package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.services.api.NewsService;

import javax.validation.Valid;
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
     * Перехват запроса определенной страницы архива новостей
     */
    @GetMapping("news_archive/{id}")
    public String getNewsArchivePage(@PathVariable int id,
                                     Model model,
                                     Authentication authentication,
                                     Pageable pageable) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
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
     * Перехват запроса страницы редактирования одной новости
     */
    @GetMapping("/edit/{id}")
    public String getNewsEditPage(@PathVariable("id") Long id,
                                  Model model,
                                  Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("news", newsService.getNewsById(id));
        return "news/news_edit";
    }

    /**
     * Перехват запроса редактирования новости
     */
    @PostMapping("/edit")
    public String addNews(@Valid @ModelAttribute("news") News news,
                          Model model,
                          Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        //TODO здесь нужно изменить новость а не сохранить
        //проверить что поля не пусты, убрать из шаблона все скрытые поля кроме id
        model.addAttribute("news", newsService.saveNews(news));
        return "redirect:/news/edit/" + news.getId();
    }


    /**
     * Перехват запроса страницы одной новости
     */
    @GetMapping("/details/{id}")
    public String getSingleNewsPage(@PathVariable Long id,
                                    Model model,
                                    Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("news", newsService.getNewsById(id));
        return "news/news_details";
    }

}
