package ru.bach.bank_api.validator;

import ru.bach.bank_api.constraint.InnConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InnValidator implements
        ConstraintValidator<InnConstraint, String> {

    @Override
    public void initialize(InnConstraint inn) {
    }

    @Override
    public boolean isValid(String inn,
                           ConstraintValidatorContext cxt) {
        return inn != null && inn.matches("[0-9]+")
                && ((inn.length() == 10) || (inn.length() == 12));
    }
}