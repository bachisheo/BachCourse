package ru.bach.bank_api.validator;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bach.bank_api.constraint.InnConstraint;
import ru.bach.bank_api.constraint.NominationConstraint;
import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_api.service.ContractorSearchService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, обеспечивающий выполнение правил валидации поля Наименования
 */
public class NominationValidator implements
        ConstraintValidator<NominationConstraint, WebContractor> {
    @Autowired
    ContractorSearchService searchService;
    @Override
    public boolean isValid(WebContractor actor, ConstraintValidatorContext context) {
        if(!searchService.existsByNomination(actor.getNomination()))
            return true;
        WebContractor actorFromServer = searchService.findByNomination(actor.getNomination());
        if(actorFromServer.getId() == actor.getId())
            return true;
        context.buildConstraintViolationWithTemplate("Контрагент с таким наименованием уже существует")
                .addPropertyNode("nomination")
                .addConstraintViolation();
        return false;
    }
}
