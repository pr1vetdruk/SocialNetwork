package ru.privetdruk.socialnetwork.controller;

import org.springframework.core.env.Environment;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.service.authentication.RegistrationServiceImpl;

@Controller
public class MainController {
    private final RegistrationServiceImpl registrationService;
    private final Environment environment;

    public MainController(RegistrationServiceImpl registrationService, Environment environment) {
        this.registrationService = registrationService;
        this.environment = environment;
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User currentUser, Model model) {
        model.addAttribute("test1", environment.getProperty("valid.global.empty"));
        if (currentUser == null) {
            registrationService.fillingCity(model);
            return "index";
        } else {
            return "redirect:/test";
        }
    }
}
