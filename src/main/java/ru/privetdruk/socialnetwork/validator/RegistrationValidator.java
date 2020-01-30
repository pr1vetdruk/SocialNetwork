package ru.privetdruk.socialnetwork.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ru.privetdruk.socialnetwork.domain.User;
import ru.privetdruk.socialnetwork.service.user.UserService;

@Component
public class RegistrationValidator {
    private final UserService userService;

    public RegistrationValidator(UserService userService) {
        this.userService = userService;
    }

    public void validatePersonalData(User user, Errors errors) {

    }

    public void validateAuthorizationData (User user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "global.empty");
        if (user.getLogin().length() < 6 || user.getLogin().length() > 32)
            errors.rejectValue("login", "registration.login.size");
        if (userService.findByLogin(user.getLogin()) != null)
            errors.rejectValue("login", "registration.login.duplicate");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "global.empty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
            errors.rejectValue("passwordError", "registration.password.size");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirmation", "global.empty");
        if (!user.getPassword().equals(user.getPasswordConfirmation()))
            errors.rejectValue("passwordConfirmation", "registration.password.equals");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "global.empty");
    }
}
