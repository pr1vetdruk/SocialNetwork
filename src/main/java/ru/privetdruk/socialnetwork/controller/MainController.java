package ru.privetdruk.socialnetwork.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.privetdruk.socialnetwork.domain.User;

import java.util.Map;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(@AuthenticationPrincipal User currentUser, Map<String, Object> model) {
        if (currentUser == null)
            return "index";
        else
            return "redirect:/test";
    }
}
