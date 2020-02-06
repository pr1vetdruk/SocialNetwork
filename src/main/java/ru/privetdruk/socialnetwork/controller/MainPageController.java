package ru.privetdruk.socialnetwork.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.service.authentication.RegistrationService;

@Controller
public class MainPageController {
    private final RegistrationService registrationService;

    public MainPageController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User authorizedUser, Model model) {
        if (authorizedUser == null) {
            registrationService.fillingCity(model);
            return "index";
        } else {
            return "redirect:/id" + authorizedUser.getId();
        }
    }
}
