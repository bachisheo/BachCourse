package ru.bach.bank_service.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

/**
 * Доменная модель контрагента. Взаимодействует с таблицей contractor
 * в базе данных со столбцами, идентичными полям.
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EnableAutoConfiguration
@Data
@Builder(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "contractor")
public class Contractor {
    /**
     * Уникальный идентификатор контрагента, задается автоматически
     * при записи в таблицу
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * ИНН контрагента
     */
    @Column(name = "inn", length = 12)
    private String inn;
    /**
     * Код постановки на учет (КПП)
     */
    @Column(name = "kpp", length = 9)
    private String kpp;

    /**
     * БИК (Банковский идентификационный код) банка, в котором открыт
     * счет контрагента
     */
    @Column(name = "bic", length = 9)
    private String bic;

    /**
     * Номер счета контрагента
     */
    @Column(name = "account_number", length = 20)
    private String accountNumber;

    /**
     * Наименование организации-контрагента
     */
    @Column(name = "nomination", length = 20)
    private String nomination;
}
