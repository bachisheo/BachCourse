package ru.bach.bank_api.constraint;
import ru.bach.bank_api.validator.AccountNumberValidator;
import ru.bach.bank_api.validator.InnValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Определение класса, проверяющего поле номер счета в
 * связке с БИКом банка
 */
@Documented
@Constraint(validatedBy = AccountNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountNumberConstraint {
    String message() default "Недопустимый ИНН";
   Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
