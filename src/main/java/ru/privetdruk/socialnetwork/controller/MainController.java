package ru.privetdruk.socialnetwork.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.service.authentication.RegistrationServiceImpl;

@Controller
public class MainController {
    private final RegistrationServiceImpl registrationService;

    public MainController(RegistrationServiceImpl registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User currentUser, Model model) {
        if (currentUser == null) {
            registrationService.fillingCity(model);
            return "index";
        } else {
            return "redirect:/test";
        }
    }
}
