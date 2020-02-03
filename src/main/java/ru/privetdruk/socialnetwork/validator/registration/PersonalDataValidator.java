package ru.privetdruk.socialnetwork.validator.registration;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.privetdruk.socialnetwork.domain.user.UserPersonalData;

@Component
public class PersonalDataValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserPersonalData.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserPersonalData personalData = (UserPersonalData) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "valid.global.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "valid.global.empty");
        errors.rejectValue("lastName", "123");
    }
}
