package ru.bach.bank_api.validator;

import org.springframework.beans.BeanWrapperImpl;
import ru.bach.bank_api.constraint.AccountNumberConstraint;
import ru.bach.bank_api.model.WebContractor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Класс, обеспечивающий выполнение правил валидации поля ИНН
 */
public class AccountNumberValidator implements
        ConstraintValidator<AccountNumberConstraint, WebContractor> {

    //TODO: 02.08.2021 проверить нужна ли здесь проверка на длину
    @Override
    public boolean isValid(WebContractor actor, ConstraintValidatorContext context) {
        String bic = actor.getBic();
        String number = actor.getAccountNumber();

        if (bic.charAt(6) == '0' && bic.charAt(7) == '0')
            return checkRKC(number, context);
        return checkCO(number, bic, context);
    }

    /**
     * Вычисление корректности указанных номера счета и БИКа для счета,
     * открытого в Кредитной организации
     * @param number номер счета
     * @param context контекстные данные и операции
     * @return true, если БИК и номер счета соответствуют друг другу
     */
    boolean checkCO(String number, String bic, ConstraintValidatorContext context) {
        String checkNumber = bic.substring(6) + number;
        if (checkSum(checkNumber) == 0)
            return true;
        //context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Счет отсутствует в данном Банке (БИК)")
                .addPropertyNode("accountNumber")
                .addConstraintViolation();
        return false;
    }

    /**
     * Вычисление корректности указанных номера счета и БИКа для счета,
     * открытого в РКЦ
     * @param number номер счета
     * @param context контекстные данные и операции
     * @return true, если БИК и номер счета соответствуют друг другу
     */
    boolean checkRKC(String number, ConstraintValidatorContext context) {
        String contrDig = '0' + bic.substring(4, 6) + number;
        if (checkSum(contrDig) == 0)
            return true;
        //context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Счет отсутствует в данном РКЦ (БИК)")
                .addPropertyNode("accountNumber")
                .addConstraintViolation();
        return false;
    }

    /**
     * Вычисление контрольной суммы
     * @param aNumber преобразованный в соответствии с БИКом номер счета
     * @return контрольная сумма
     */
    Long checkSum(String aNumber) {
        int[] wt = {7, 1, 3};
        long checkSum = 0L;
        for (int i = 0; i < aNumber.length(); i++)
            checkSum += (long) Character.getNumericValue(aNumber.charAt(i)) * wt[i % wt.length];
        return checkSum % 10;
    }
}
