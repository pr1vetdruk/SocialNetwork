package ru.privetdruk.socialnetwork.validator;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.privetdruk.socialnetwork.domain.user.User;
import ru.privetdruk.socialnetwork.domain.user.dto.UserPersonalDataDto;
import ru.privetdruk.socialnetwork.service.authentication.RegistrationService;
import ru.privetdruk.socialnetwork.service.user.UserService;

import java.util.Objects;

@Component
public class RegistrationValidator {
    private final Environment environment;
    private final RegistrationService registrationService;

    public RegistrationValidator(Environment environment, RegistrationService registrationService) {
        this.environment = environment;
        this.registrationService = registrationService;
    }

    public void validatePersonalData(UserPersonalDataDto personalDataDto, Errors errors) {
        if (!errors.hasFieldErrors("cityId") && registrationService.findCity(personalDataDto.getCityId()) == null) {
            errors.rejectValue("cityId", "validation.registration.cityId.none", Objects.requireNonNull(environment.getProperty("validation.registration.cityId.none")));
        }
    }

    public void validateAuthorizationData(User user, Errors errors) {
        if (registrationService.isFoundUser(user.getLogin())) {
            errors.rejectValue("login", "validation.registration.login.duplicate", Objects.requireNonNull(environment.getProperty("validation.registration.login.duplicate")));
        }
    }
}
