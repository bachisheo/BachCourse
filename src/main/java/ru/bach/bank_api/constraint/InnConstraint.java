package ru.bach.bank_api.constraint;

import ru.bach.bank_api.validator.InnValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Определение класса, проверяющего поле ИНН
 */
@Documented
@Constraint(validatedBy = InnValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InnConstraint {
    String message() default "Недопустимый ИНН";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
