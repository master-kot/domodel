package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.services.api.VoteService;

/**
 * Контроллер голосований
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/votes")
@RequiredArgsConstructor
public class VotesController {

    // Сервис голосований
    private final VoteService voteService;
}
