package ru.privetdruk.socialnetwork.validator.constraint;

import ru.privetdruk.socialnetwork.validator.annotation.DateBirth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateBirthConstraintValidator implements ConstraintValidator<DateBirth, LocalDate> {
   public void initialize(DateBirth constraint) {
   }

   public boolean isValid(LocalDate target, ConstraintValidatorContext context) {
      if (target == null) {
         return true;
      }

      long years = ChronoUnit.YEARS.between(target, LocalDate.now());

      return years >= 14;
   }
}
