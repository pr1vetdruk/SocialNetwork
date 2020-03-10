package ru.privetdruk.socialnetwork.validator;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.privetdruk.socialnetwork.domain.user.dto.UserDto;
import ru.privetdruk.socialnetwork.domain.user.dto.UserPersonalDataDto;
import ru.privetdruk.socialnetwork.service.GeneralService;
import ru.privetdruk.socialnetwork.service.user.UserService;

import java.util.Objects;

@Component
public class UserDataValidator {
    private final Environment environment;
    private final GeneralService generalService;
    private final UserService userService;

    public UserDataValidator(Environment environment, GeneralService generalService, UserService userService) {
        this.environment = environment;
        this.generalService = generalService;
        this.userService = userService;
    }

    public void validatePersonalData(UserPersonalDataDto personalDataDto, Errors errors) {
        if (!errors.hasFieldErrors("cityId") && generalService.findCity(personalDataDto.getCityId()) == null) {
            errors.rejectValue("cityId", "validation.registration.cityId.none", Objects.requireNonNull(environment.getProperty("validation.registration.cityId.none")));
        }
    }

    public void validateAuthorizationData(UserDto userDto, Errors errors) {
        if (!errors.hasFieldErrors("login") && userService.isExist(userDto.getLogin())) {
            errors.rejectValue("login", "validation.registration.login.duplicate", Objects.requireNonNull(environment.getProperty("validation.registration.login.duplicate")));
        }

        if (!errors.hasFieldErrors("password")
                && !errors.hasFieldErrors("passwordConfirmation")
                && !userDto.getPassword().equals(userDto.getPasswordConfirmation())) {
            errors.rejectValue("password", "validation.registration.password.equals", Objects.requireNonNull(environment.getProperty("validation.registration.password.equals")));
            errors.rejectValue("passwordConfirmation", "validation.registration.password.equals", "");
        }
    }
}
