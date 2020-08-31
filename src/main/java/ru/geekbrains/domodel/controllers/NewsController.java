package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.services.api.NewsService;

import javax.validation.Valid;
import java.security.Principal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер новостей
 */
//todo перейти на рест, поднять все методы


 /**
 * Контроллер новостей
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    // Сервис новостей
    private final NewsService newsService;

    /**
     * Перехват запроса определенной страницы архива новостей
     */
    //todo починить, сделать пагинацию, переписать на REST
    @GetMapping("/archive/{id}")
    public String getNewsArchivePage(@PathVariable int id,
                                     Model model,
                                     Authentication authentication,
                                     Pageable pageable) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
//        if (id == 0) id =1;
//        Pageable pageRequest = PageRequest.of(id, 2);
//        model.addAttribute("all", newsService.findAll(pageRequest));
        model.addAttribute("newsArchive", newsService.readNewsArchive(authentication));
//        Page<News> page = newsService.findAll(pageRequest);
//        model.addAttribute("page", page);
        return "news/news_archive";
    }



    /**
     * Перехват запроса добавления новости
     */
    //todo отправить в главный контроллер? переписать на REST, изменить секьюрити на токены, изменить News на NewsDto?
    @PostMapping("")
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
    //todo переделать на REST, переделать секьюрити на токены
    @GetMapping("/{id}")
    public String getSingleNewsPage(@PathVariable Long id,
                                    Model model,
                                    Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("news", newsService.readNewsById(id));
        return "news/news_details";
    }

     /**
      * Перехват запроса изменения одной новости
      */
     //todo Написать метод на REST + секьюрити на токены
     @PostMapping("/{id}")
     public String setSingleNewsPage(@PathVariable Long id,
                                     Model model,
                                     Principal principal) {
         if (principal != null) {
             model.addAttribute("username", principal.getName());
         }
         model.addAttribute("news", newsService.readNewsById(id));
         return "news/news_details";
     }

     /**
      * Перехват запроса новости для изменения ее видимости (условное удаление)
      * @param id номер новости
      */
     //todo добавить секьюрити. изменить News на NewsDto?
    @PatchMapping ("/{id}")
     public News updateVisibilityNewsById(@PathVariable Long id){
        return newsService.updateVisibilityNewsById(id, false);
    }


     /**
      * Перехват запроса новости для изменения ее видимости (условное удаление)
      * @param id номер новости
      */
     //todo добавить секьюрити. изменить News на NewsDto?
     @PatchMapping ("/{id}")
     public News updatePinnedNewsById(@PathVariable Long id){
         return newsService.updatePinningNewsById(id, true);
     }

}
