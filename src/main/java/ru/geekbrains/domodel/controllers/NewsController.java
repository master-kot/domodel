package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.dto.NewsRequestDto;
import ru.geekbrains.domodel.security.jwt.JwtTokenProvider;
import ru.geekbrains.domodel.services.api.NewsService;

import java.security.Principal;
import java.util.List;
import io.swagger.annotations.ApiOperation;

/**
 * Контроллер новостей
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    // Тип объекта
    private final String CONSUME_TYPE = "application/json";

    // Список необходимых сервисов
    private final NewsService newsService;
//    private final JwtTokenFilter jwtTokenFilter;


    /*
     * СОГЛАШЕНИЕ О НАИМЕНОВАНИИ МЕТОДОВ СЕРВИСОВ
     * NewsDto getById(Long id) найти объект по параметру
     * Collection<NewsDto> getAll() найти все объекты
     * Collection<NewsDto> getAllByUser(UserDto userDto) найти все объекты по параметру
     * News update(NewsDto newsDto) изменить объект
     * News save(NewsDto newsDto) сохранить объект
     * Collection<NewsDto> saveAll(Collection<NewsDto> newsDtoCollection) сохранить список объектов
     * void delete(NewsDto newsDto) удалить конкретный объект
     * Long deleteById(Long id) удалить объект по параметру
     * void deleteAll(Collection<NewsDto> newsDtoCollection) удалить список объектов
     */
    //todo  добавить секьюрити
    @GetMapping("/archive/{id}")
    public List<NewsDto> getNewsArchivePage(@PathVariable int id,
                                            Model model,
                                            Authentication authentication,
                                            Pageable pageable,
                                            JwtTokenProvider jwtTokenProvider) {
//        if (authentication != null) {
//            model.addAttribute("username", authentication.getName());
//        }
//        if (id == 0) id =1;
//        Pageable pageRequest = PageRequest.of(id, 2);
//        model.addAttribute("all", newsService.findAll(pageRequest));
//        model.addAttribute("newsArchive", newsService.readNewsArchive(authentication));
//        Page<News> page = newsService.findAll(pageRequest);
//        model.addAttribute("page", page);

        List <NewsDto> newsArchive = newsService.getNewsArchive(id);
        return newsArchive;
    }



    /**
     * Перехват запроса добавления новости
     */
    //todo починить, отправить в главный контроллер? изменить секьюрити на токены
    @PostMapping("/add")
    public NewsDto createNews(@RequestBody NewsDto newsDto) {
//        if (principal != null) {
//            model.addAttribute("username", principal.getName());
//        }
//        model.addAttribute("news", newsService.saveNews(news));
        //TODO проверить что поля не пусты, убрать из шаблона все скрытые поля кроме id
        return newsService.saveNews(newsDto);
    }

    /**
     * Перехват запроса страницы одной новости
     */
    //todo  добавить секьюрити
    @GetMapping("/{id}")
    public NewsDto getSingleNewsPage(@PathVariable Long id,
                                    Model model,
                                    Principal principal) {
//        if (principal != null) {
//            model.addAttribute("username", principal.getName());
//        }
//        model.addAttribute("news", newsService.readNewsById(id));
        return newsService.getNewsById(id);
    }

     /**
      * Перехват запроса изменения одной новости
      */
     //todo секьюрити на токены
     @PostMapping("/{id}")
     public NewsDto setSingleNewsPage(@PathVariable Long id,
                                     @RequestBody NewsDto newsDto,
                                     Model model,
                                     Principal principal) {
//         if (principal != null) {
//             model.addAttribute("username", principal.getName());
//         }
//         model.addAttribute("news", newsService.getNewsById(id));
//         return "news/news_details";
         return newsService.updateNewsById(id, newsDto);
     }

     /**
      * Перехват запроса новости для изменения ее видимости (условное удаление)
      * @param id номер новости
      */
     //todo добавить секьюрити
    @PostMapping ("/visibility/{id}")
     public boolean updateVisibilityNewsById(@PathVariable Long id, @RequestBody boolean visible){
        return newsService.updateVisibilityNewsById(id, visible);
    }

     /**
      * Перехват запроса новости для изменения ее видимости (условное удаление)
      * @param id номер новости
      */
     //todo добавить секьюрити
     @PostMapping ("/pinned/{id}")
     public boolean updatePinnedNewsById(@PathVariable Long id, @RequestBody boolean pinned){
         return newsService.updatePinningNewsById(id, pinned);
     }

    /**
     * Создает новость
     */
    @ApiOperation(value = "Создает новость")
    @PostMapping(consumes = CONSUME_TYPE)
    public ResponseEntity<NewsDto> createNews(@RequestBody NewsRequestDto newsRequestDto,
                                              Authentication authentication) {
        NewsDto newsDto = newsService.save(newsRequestDto, authentication);
        if (newsDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsDto, HttpStatus.OK);
    }
}
