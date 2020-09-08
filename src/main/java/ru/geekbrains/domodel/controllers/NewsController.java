package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.dto.NewsRequest;
import ru.geekbrains.domodel.services.api.NewsService;

import java.util.List;

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

    @ApiOperation(value = "Выдает список новостей определенной страницы архива")
    @GetMapping("/archive/{id}")
    public ResponseEntity<List<NewsDto>> readNewsArchiveByPageId(@PathVariable int id,
                                                                 Authentication authentication) {
        return getListResponseEntity(newsService.getArchive(id, authentication));
    }

    @ApiOperation(value = "Создает новость")
    @PostMapping(consumes = CONSUME_TYPE)
    public ResponseEntity<NewsDto> createNews(@RequestBody NewsRequest newsRequest,
                                              Authentication authentication) {
        return getNewsDtoResponseEntity(newsService.save(newsRequest, authentication));
    }

    @ApiOperation(value = "Выдает новость по ее номеру")
    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> readNewsById(@PathVariable Long id,
                                                Authentication authentication) {
        return getNewsDtoResponseEntity(newsService.getById(id, authentication));
    }
    
    @ApiOperation(value = "Изменяет новость по ее номеру")
    @PostMapping("/{id}")
    public ResponseEntity<NewsDto> updateNewsById(@PathVariable Long id,
                                                  @RequestBody NewsDto newsDto,
                                                  Authentication authentication) {

        return getNewsDtoResponseEntity(newsService.updateById(id, newsDto, authentication));
    }

    @ApiOperation(value = "Изменяет видимость новости (условное удаление) по ее номеру")
    @PostMapping ("/{id}/visible")
     public ResponseEntity<Boolean> updateVisibilityNewsById(@PathVariable Long id,
                                                             @RequestBody boolean visible,
                                                             Authentication authentication){
        return new ResponseEntity<>(newsService.updateVisibilityById(id, visible, authentication), HttpStatus.OK);
    }

     @ApiOperation(value = "Изменяет параметр закрепления новости по ее номеру")
     @PostMapping ("/{id}/pinned")
     public ResponseEntity<Boolean> updatePinningNewsById(@PathVariable Long id,
                                                          @RequestBody boolean pinned,
                                                          Authentication authentication){
         return new ResponseEntity<>(newsService.updatePinningById(id, pinned, authentication), HttpStatus.OK);
     }

    /**
     * Формирует необходимый ответ в зависимости от содержания списка newsDtoList
     */
    private ResponseEntity<List<NewsDto>> getListResponseEntity(List<NewsDto> newsDtoList) {
        return newsDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(newsDtoList, HttpStatus.OK);
    }

    /**
     * Формирует необходимый ответ в зависимости от содержания newsDto
     */
    private ResponseEntity<NewsDto> getNewsDtoResponseEntity(NewsDto newsDto) {
        return newsDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(newsDto, HttpStatus.OK);
    }
}
