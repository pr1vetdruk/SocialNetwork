package ru.privetdruk.socialnetwork.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.service.GeneralService;

@Controller
public class MainPageController {
    private final GeneralService generalService;

    public MainPageController(GeneralService generalService) {
        this.generalService = generalService;
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User authorizedUser, Model model) {
        if (authorizedUser == null) {
            generalService.fillingCity(model);
            return "index";
        } else {
            return "redirect:/id" + authorizedUser.getId();
        }
    }
}
