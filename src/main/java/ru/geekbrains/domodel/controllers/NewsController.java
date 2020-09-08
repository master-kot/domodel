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

    @ApiOperation(value = "Выдает список новостей определенной страницы архива")
    @GetMapping("/archive/{id}")
    public ResponseEntity<List<NewsDto>> readNewsArchiveByPageId(@PathVariable int id,
                                            Authentication authentication) {
        List <NewsDto> newsDtoList = newsService.getArchive(id, authentication);
        if (newsDto.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsDtoList, HttpStatus.OK);
    }

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

    @ApiOperation(value = "Выдает новость по ее номеру")
    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> readNewsById(@PathVariable Long id,
                                     Authentication authentication) {
        NewsDto newsDto = newsService.getById(id, authentication);
        if (newsDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsDto, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Изменяет новость по ее номеру")
    @PostMapping("/{id}")
    public ResponseEntity<NewsDto> updateNewsById(@PathVariable Long id,
                                     @RequestBody NewsDto newsDto,
                                      Authentication authentication) {
        NewsDto newsDto = newsService.updateNewsById(id, newsDto, authentication)
        if (newsDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Изменяет видимость новости (условное удаление) по ее номеру")
    @PostMapping ("/{id}/visible")
     public ResponseEntity<Boolean> updateVisibilityNewsById(@PathVariable Long id,
                                             @RequestBody boolean visible,
                                             Authentication authentication){
        return new ResponseEntity<>(newsService.updateVisibilityNewsById(id, visible, authentication), HttpStatus.OK);
    }

     @ApiOperation(value = "Изменяет параметр закрепления новости по ее номеру")
     @PostMapping ("/{id}/pinned")
     public ResponseEntity<Boolean> updatePinningNewsById(@PathVariable Long id,
                                         @RequestBody boolean pinned,
                                         Authentication authentication){
         return new ResponseEntity<>(newsService.updatePinningNewsById(id, pinned, authentication), HttpStatus.OK);
     }
}
