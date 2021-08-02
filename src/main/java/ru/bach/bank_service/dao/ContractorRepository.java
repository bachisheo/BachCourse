package ru.bach.bank_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bach.bank_service.entity.Contractor;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    /**
     * Поиск контрагента по наименованию
     * @param nomination наименование контрагента
     * @return контрагент
     */
    Optional<Contractor> findByNomination (String nomination);

    /**
     * Поиск контрагента по номеру счета и БИК
     * @param bic Банковский идентификационный номер (БИК)
     * @param accountNumber номер счета контрагента
     * @return контрагент
     */
    Optional<Contractor> findByBicAndAccountNumber (String bic, String accountNumber);

    /**
     * Удаление контрагента по ID
     * @param id наименование контрагента
     */
    void deleteById(long id);

    /**
     * Удалить все записи в базе данных
     */
    void deleteAll();
    /**
     * Проверка наличия контрагентов с наименованием из списка
     */
    boolean existsByNominationIn (List<String> names);



}
