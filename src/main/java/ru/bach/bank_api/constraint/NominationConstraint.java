package ru.bach.bank_api.constraint;

import ru.bach.bank_api.validator.NominationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Определение класса, проверяющего наименование
 */
@Documented
@Constraint(validatedBy = NominationValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NominationConstraint {
    String message() default "Недопустимое наименование";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
