package ru.bach.bank_api.validator;

import ru.bach.bank_api.constraint.InnConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Класс, обеспечивающий выполнение правил валидации поля ИНН
 */
public class InnValidator implements
        ConstraintValidator<InnConstraint, String> {

    @Override
    public void initialize(InnConstraint inn) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!(value.length() == 10 || value.length() == 12)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Поле должно содержать 10 или 12 цифр")
                    .addBeanNode()
                    .addConstraintViolation();
            return false;
        }
        try {
            Long.parseLong(value);
        } catch (Exception e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Ошибка конвертации")
                    .addBeanNode()
                    .addConstraintViolation();
            return false;
        }
        if (!checkOrgInn(value) || checkPhysInn(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Ошибочная контрольная сумма")
                    .addBeanNode()
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    /**
     * Метод проверки ИНН юридического лица (10-значного)
     *
     * @param inn ИНН
     * @return true при допустимом ИНН
     */
    private boolean checkOrgInn(String inn) {
        if (inn.length() != 10)
            return false;
        int[] wt = {2, 4, 10, 3, 5, 9, 4, 6, 8, 0};
        long sum = 0L;
        for (int i = 0; i < inn.length(); i++)
            sum += (long) wt[i] * Character.getNumericValue(inn.charAt(i));
        sum = (sum % 11) % 10;
        return sum == Character.getNumericValue(inn.charAt(inn.length() - 1));
    }

    /**
     * Метод проверки ИНН физического лица (12-значного)
     *
     * @param inn ИНН
     * @return true при допустимом ИНН
     */
    private boolean checkPhysInn(String inn) {
        if (inn.length() != 12)
            return false;
        int[] wt = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0};
        long firstSum = 0L;
        long secondSum = 0L;
        for (int i = 0; i < wt.length - 1; i++) {
            firstSum += wt[i + 1] * (long) Character.getNumericValue(inn.charAt(i));
        }
        firstSum = (firstSum % 11) % 10;
        for (int i = 0; i < wt.length; i++) {
            secondSum += wt[i] * (long) Character.getNumericValue(inn.charAt(i));
        }
        secondSum = (secondSum % 11) % 10;
        return (long) Character.getNumericValue(inn.charAt(10)) == firstSum &&
                (long) Character.getNumericValue(inn.charAt(11)) == secondSum;
    }
}
