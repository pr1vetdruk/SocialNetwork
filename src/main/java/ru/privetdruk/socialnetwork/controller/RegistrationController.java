package ru.privetdruk.socialnetwork.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.privetdruk.socialnetwork.domain.User;
import ru.privetdruk.socialnetwork.service.RegistrationService;
import ru.privetdruk.socialnetwork.utils.ControllerUtils;
import ru.privetdruk.socialnetwork.utils.ModelUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String registration(@AuthenticationPrincipal User user, Model model) {
        if (user != null)
            return "redirect:/";

        registrationService.fillingCities(model);
        return "registration";
    }

    @PostMapping(value = "/registration", params = "continue")
    public String personalData(@Valid User user,
                               BindingResult bindingResult,
                               Model model,
                               HttpServletRequest request) {
        boolean isFirstName = ModelUtils.isEmpty(user.getFirstName(), model, "firstNameError", "Введите ваше имя");
        boolean isLastName = ModelUtils.isEmpty(user.getLastName(), model, "lastNameError", "Введите вашу фамилию");
        boolean isCity = ModelUtils.isEmpty(user.getCityId(), model, "cityError", "Выберите город");
        boolean isDateBirth = ModelUtils.isEmpty(user.getDateBirth(), model, "dateBirthError", "Заполните дату рождения");

        if (isFirstName || isLastName || isCity || isDateBirth || bindingResult.hasErrors()) {
            if (isCity)
                model.addAttribute("city", registrationService.findCity(user.getCityId()));
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            registrationService.fillingCities(model);
        } else {
            request.getSession().setAttribute("personalData", user);
            model.addAttribute("nextRegistrationStep", "true");
        }

        return "registration";
    }

    @PostMapping(value = "/registration", params = "registration")
    public String addUser(@RequestParam("passwordConfirmation") String passwordConfirmation, @Valid User user, Model model,
                          HttpServletRequest request) {
        boolean isLoginEmpty = ModelUtils.isEmpty(user.getLogin(), model, "loginError", "Введите логин");
        boolean isPasswordEmpty = ModelUtils.isEmpty(user.getPassword(), model, "passwordError", "Введите пароль");
        boolean isPasswordConfirmEmpty = ModelUtils.isEmpty(user.getLogin(), model, "passwordConfirmationError", "Повторите пароль");

        if (registrationService.isUserExists(user.getLogin())) {
            model.addAttribute("loginError", "Пользователь с таким логином уже существует");
            return "registration";
        } else {
            registrationService.addNewUser(user, (User) Objects.requireNonNull(request.getSession().getAttribute("personalData")));
            return "redirect:/";
        }
    }
}
