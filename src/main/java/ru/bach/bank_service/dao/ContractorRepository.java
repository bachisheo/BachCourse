package ru.bach.bank_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bach.bank_service.entity.Contractor;

import java.util.List;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    /**
     * Поиск контрагента по наименованию
     * @param nomination наименование контрагента
     * @return контрагент
     */
    Contractor findByNomination (String nomination);

    /**
     * Поиск контрагента по номеру счета и БИК
     * @param bic Банковский идентификационный номер (БИК)
     * @param accountNumber номер счета контрагента
     * @return контрагент
     */
    Contractor findByAccountNumberAndBic (String bic, String accountNumber);

    /**
     * Удаление контрагента по наименованию
     * @param nomination наименование контрагента
     */
    void deleteByNomination (String nomination);

    void deleteByAccountNumberAndBic(String bic, String accountNumber);

    /**
     * Проверка наличия контрагентов с наименованием из списка
     */
    boolean existsByNominationIn (List<String> names);

}
