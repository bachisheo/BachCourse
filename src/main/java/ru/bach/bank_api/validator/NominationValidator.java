package ru.bach.bank_api.validator;

import ru.bach.bank_api.constraint.InnConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Класс, обеспечивающий выполнение правил валидации поля Наименования
 */
public class NominationValidator implements
        ConstraintValidator<InnConstraint, String> {

    @Override
    public void initialize(InnConstraint inn) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    }
}
