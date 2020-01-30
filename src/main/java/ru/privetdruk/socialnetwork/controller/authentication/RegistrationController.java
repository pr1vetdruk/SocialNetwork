package ru.privetdruk.socialnetwork.controller.authentication;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.privetdruk.socialnetwork.domain.User;
import ru.privetdruk.socialnetwork.service.authentication.RegistrationServiceImpl;
import ru.privetdruk.socialnetwork.service.authentication.SecurityService;
import ru.privetdruk.socialnetwork.utils.ControllerUtils;
import ru.privetdruk.socialnetwork.utils.ModelUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@Controller
public class RegistrationController {
    private final RegistrationServiceImpl registrationService;
    private final SecurityService securityService;

    public RegistrationController(RegistrationServiceImpl registrationService, SecurityService securityService) {
        this.registrationService = registrationService;
        this.securityService = securityService;
    }

    @GetMapping("/registration")
    public String registration(@AuthenticationPrincipal User user, Model model) {
        if (user != null)
            return "redirect:/";

        registrationService.fillingCity(model);
        return "registration";
    }

    @PostMapping(value = "/registration", params = "continue")
    public String personalData(@Valid User user,
                               BindingResult bindingResult,
                               Model model,
                               HttpServletRequest request) {
        boolean isFirstNameEmpty = false; //ModelUtils.isEmpty(user.getFirstName(), model, "firstNameError", "Введите ваше имя");
        boolean isLastNameEmpty = ModelUtils.isEmpty(user.getLastName(), model, "lastNameError", "Введите вашу фамилию");
        boolean isCityEmpty = ModelUtils.isEmpty(user.getCityId(), model, "cityError", "Выберите город");
        boolean isDateBirthEmpty = ModelUtils.isEmpty(user.getDateBirth(), model, "dateBirthError", "Заполните дату рождения");
        bindingResult.rejectValue("firstName", "global.empty");
        if (isFirstNameEmpty || isLastNameEmpty || isCityEmpty || isDateBirthEmpty || bindingResult.hasErrors()) {
            if (!isCityEmpty)
                model.addAttribute("city", registrationService.findCity(user.getCityId()));
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            registrationService.fillingCity(model);
        } else {
            request.getSession().setAttribute("personalData", user);
            model.addAttribute("nextRegistrationStep", "true");
        }

        return "registration";
    }

    @PostMapping(value = "/registration", params = "registration")
    public String addUser(@Valid User user,
                          BindingResult bindingResult,
                          Model model,
                          HttpServletRequest request) {
        boolean isLoginEmpty = ModelUtils.isEmpty(user.getLogin(), model, "loginError", "Введите логин");
        boolean isPasswordEmpty = ModelUtils.isEmpty(user.getPassword(), model, "passwordError", "Введите пароль");
        boolean isPasswordConfirmEmpty = ModelUtils.isEmpty(user.getPasswordConfirmation(), model, "passwordConfirmationError", "Повторите пароль");
        boolean isEmailEmpty = ModelUtils.isEmpty(user.getEmail(), model, "emailError", "Введите E-mail");

        if (isLoginEmpty || isPasswordEmpty || isPasswordConfirmEmpty || isEmailEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
        } else if (registrationService.isFoundUser(user.getLogin())) {
            model.addAttribute("loginError", "Пользователь с таким логином уже существует");
        } else {
            registrationService.addUser(user, (User) Objects.requireNonNull(request.getSession().getAttribute("personalData")));
            request.getSession().removeAttribute("personalData");
            securityService.autoLogin(user.getLogin(), user.getPasswordConfirmation());
            return "redirect:/";
        }

        model.addAttribute("nextRegistrationStep", "true");
        return "registration";
    }
}
