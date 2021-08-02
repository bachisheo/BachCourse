package ru.bach.bank_api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.bach.bank_api.constraint.AccountNumberConstraint;
import ru.bach.bank_api.constraint.InnConstraint;
import ru.bach.bank_api.constraint.NominationConstraint;

import javax.validation.constraints.*;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AccountNumberConstraint
@NominationConstraint
@ApiModel(value = "Канальная модель контрагента")
public class WebContractor {
    @ApiModelProperty(
            value = "ИД контрагента (скрытое поле)",
            name = "id",
            dataType = "long",
            example = "123")
    private long id;

    @ApiModelProperty(
            value = "ИНН контрагента",
            name = "inn",
            dataType = "String",
            example = "123")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "ИНН содержит только цифры")

    @InnConstraint
    private String inn;

    @ApiModelProperty(
            value = "Код постановки на учет (КПП)",
            name = "kpp",
            dataType = "String",
            example = "123")
    @Size(max = 9, min = 9, message = "КПП содержит 9 символов")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "КПП содержит только цифры")
    private String kpp;

    @ApiModelProperty(
            value = "БИК банка контрагента",
            name = "bic",
            dataType = "String",
            example = "123")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "БИК банка содержит только цифры")
    private String bic;


    @ApiModelProperty(
            value = "Номер счета",
            name = "accountNumber",
            dataType = "String",
            example = "123")
    @Pattern(regexp = "(^[(0-9)]+)$", message = "Номер счета содержит только цифры")
    @Size(max = 20, min = 20, message = "Номер счета состоит из 20 символов")

    private String accountNumber;

    @ApiModelProperty(
            value = "Наименование",
            name = "nomination",
            dataType = "String",
            example = "123")
    @Size(max = 20, min = 1, message = "Наименование содержит от 1 до 20 символов")
    private String nomination;

    @Override
    public boolean equals(Object o) {
        WebContractor owc = (WebContractor) o;
        if(this.nomination.equals(owc.getNomination())
        && this.inn.equals(owc.getInn())
        && this.kpp.equals(owc.getKpp())
        && this.accountNumber.equals(owc.getAccountNumber())
        && this.bic.equals(owc.getBic()))
            return true;
        return false;
    }

}

