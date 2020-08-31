package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.VoteService;

import java.security.Principal;

/**
 * Контроллер голосований
 */
@Controller
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VotesController {

    // Сервис голосований
    private final VoteService voteService;

    /**
     * Перехват запроса главной страницы голосований
     */
    @GetMapping("")
    public String getAppealsPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "votes/votes_user";
    }
}
