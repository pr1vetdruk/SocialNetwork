package ru.privetdruk.socialnetwork.validator;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.domain.user.UserPersonalData;
import ru.privetdruk.socialnetwork.service.user.UserService;

@Component
public class RegistrationValidator {
    private final Environment environment;
    private final UserService userService;

    public RegistrationValidator(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    public void personalDataValidate(UserPersonalData personalData, Model model) {

    }

    public void authorizationDataValidate(User user, Model model) {
        if (StringUtils.isEmpty(user.getLogin()))
            model.addAttribute("loginError", environment.getProperty("valid.global.empty"));
        else if (user.getLogin().length() < 1 || user.getLogin().length() > 32)
            model.addAttribute("loginError", environment.getProperty("valid.registration.login.size"));
        else if (userService.findByLogin(user.getLogin()) != null)
            model.addAttribute("loginError", environment.getProperty("valid.registration.login.duplicate"));

        if (StringUtils.isEmpty(user.getPassword()))
            model.addAttribute("passwordError", environment.getProperty("valid.global.empty"));
        else if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
            model.addAttribute("passwordError", "valid.registration.password.size");

        if (StringUtils.isEmpty(user.getPasswordConfirmation()))
            model.addAttribute("passwordConfirmationError", environment.getProperty("valid.global.empty"));
        else if (!user.getPassword().equals(user.getPasswordConfirmation()))
            model.addAttribute("passwordConfirmationError", environment.getProperty("valid.registration.password.equals"));

        if (StringUtils.isEmpty(user.getEmail()))
            model.addAttribute("emailError", environment.getProperty("valid.global.empty"));
    }
}
