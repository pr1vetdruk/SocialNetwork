package ru.privetdruk.socialnetwork.validator.annotation;

import ru.privetdruk.socialnetwork.validator.constraint.DateBirthConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateBirthConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateBirth {
    String message() default "{validation.personalData.dateBirth.difference}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
