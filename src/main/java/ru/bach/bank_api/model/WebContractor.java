package ru.bach.bank_api.model;
import io.swagger.annotations.ApiModel;
import lombok.*;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Канальная модель контрагента")
public class WebContractor {
    private long webId;

    private String inn;

    private String kpp;

    private String bic;

    private String accountNumber;

    private String nomination;
}
