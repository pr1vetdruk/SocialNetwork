package ru.privetdruk.socialnetwork.validator.constraint;

import ru.privetdruk.socialnetwork.validator.annotation.DateBirth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class DateBirthConstraintValidator implements ConstraintValidator<DateBirth, Date> {
   public void initialize(DateBirth constraint) {
   }

   public boolean isValid(Date target, ConstraintValidatorContext context) {
      if (target == null) {
         return true;
      }

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(target);
      int fieldYear = calendar.get(Calendar.YEAR);
      calendar.setTime(new Date());
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);

      return (currentYear - fieldYear) >= 14;
   }
}
