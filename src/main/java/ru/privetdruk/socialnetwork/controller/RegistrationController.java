package ru.privetdruk.socialnetwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.privetdruk.socialnetwork.domain.User;
import ru.privetdruk.socialnetwork.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() { return "registration"; }

    @PostMapping("/registration")
    public String addUser(@RequestParam("passwordConfirmation") String passwordConfirmation, @Valid User user, Model model) {
        int i = 1;
        int b = 2;
        return "test";
    }
}
