package ru.privetdruk.socialnetwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.privetdruk.socialnetwork.domain.User;
import ru.privetdruk.socialnetwork.service.RegistrationService;
import ru.privetdruk.socialnetwork.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final RegistrationService registrationService;

    public RegistrationController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String registration() { return "registration"; }

    @PostMapping("/registration")
    public String addUser(@RequestParam("passwordConfirmation") String passwordConfirmation, @Valid User user, Model model) {
        boolean isLoginEmpty = registrationService.isEmpty(user.getLogin(), model, "loginError", "Введите логин");
        boolean isPasswordEmpty = registrationService.isEmpty(user.getPassword(), model, "passwordError", "Введите пароль");
        boolean isPasswordConfirmEmpty = registrationService.isEmpty(user.getLogin(), model, "passwordConfirmationError", "Повторите пароль");

        if (registrationService.isUserExists(user.getLogin())) {
            model.addAttribute("loginError", "Пользователь с таким логином уже существует");
            return "registration";
        } else {
            registrationService.addNewUser(user);
            return "redirect:/";
        }
    }
}
