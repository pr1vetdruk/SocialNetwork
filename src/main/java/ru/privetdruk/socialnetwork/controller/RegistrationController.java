package ru.privetdruk.socialnetwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.privetdruk.socialnetwork.domain.User;
import ru.privetdruk.socialnetwork.repository.CityRepository;
import ru.privetdruk.socialnetwork.service.RegistrationService;
import ru.privetdruk.socialnetwork.utils.ControllerUtils;
import ru.privetdruk.socialnetwork.utils.ModelUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        registrationService.fillingCities(model);
        return "registration";
    }

    @PostMapping(value = "/registration", params = "continue")
    public String personalData(@Valid User user,
                               BindingResult bindingResult,
                               Model model) {
        boolean isFirstName = ModelUtils.isEmpty(user.getFirstName(), model, "firstNameError", "Введите ваше имя");
        boolean isLastName = ModelUtils.isEmpty(user.getLastName(), model, "lastNameError", "Введите вашу фамилию");
        boolean isCity = ModelUtils.isEmpty(user.getCity(), model, "cityError", "Выберите город");
        boolean isDateBirth = ModelUtils.isEmpty(user.getDateBirth(), model, "dateBirthError", "Заполните дату рождения");

        model.addAttribute("dateBirth", user.getDateBirth());

        if (isFirstName || isLastName || isCity || isDateBirth || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            registrationService.fillingCities(model);
        } else {
            model.addAttribute("nextRegistrationStep", "true");
        }

        return "registration";
    }

    @PostMapping(value = "/registration", params = "registration")
    public String addUser(@RequestParam("passwordConfirmation") String passwordConfirmation, @Valid User user, Model model) {
        boolean isLoginEmpty = ModelUtils.isEmpty(user.getLogin(), model, "loginError", "Введите логин");
        boolean isPasswordEmpty = ModelUtils.isEmpty(user.getPassword(), model, "passwordError", "Введите пароль");
        boolean isPasswordConfirmEmpty = ModelUtils.isEmpty(user.getLogin(), model, "passwordConfirmationError", "Повторите пароль");

        if (registrationService.isUserExists(user.getLogin())) {
            model.addAttribute("loginError", "Пользователь с таким логином уже существует");
            return "registration";
        } else {
            registrationService.addNewUser(user);
            return "redirect:/";
        }
    }
}
