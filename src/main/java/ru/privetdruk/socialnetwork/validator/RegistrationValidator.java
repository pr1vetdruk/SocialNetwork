package ru.privetdruk.socialnetwork.validator;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.domain.user.UserPersonalData;
import ru.privetdruk.socialnetwork.service.user.UserService;

import java.util.Objects;

@Component
public class RegistrationValidator implements Validator {
    private final Environment environment;
    private final UserService userService;

    public RegistrationValidator(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserPersonalData.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (userService.findByLogin(user.getLogin()) != null)
            errors.rejectValue("login", "valid.registration.login.duplicate", Objects.requireNonNull(environment.getProperty("valid.registration.login.duplicate")));
    }
}
