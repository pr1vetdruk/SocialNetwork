package ru.privetdruk.socialnetwork.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.service.user.UserService;

@Controller
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id{id}")
    public String showProfile(@AuthenticationPrincipal User authorizedUser, @PathVariable Long id, Model model) {
        if (authorizedUser.getId().equals(id)) {
            model.addAttribute("user", authorizedUser);
        } else {
            User user = userService.findById(id);
            if (user == null) {
                return "/error/4xx/404";
            }
            model.addAttribute("user", user);
        }

        return "profile";
    }
}