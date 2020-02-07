package ru.privetdruk.socialnetwork.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.privetdruk.socialnetwork.domain.user.User;

@Controller
public class ProfileController {
    @GetMapping("/id{id}")
    public String showProfile(@AuthenticationPrincipal User authorizedUser, @PathVariable Long id, Model model) {
        //test
        return "profile";
    }
}
