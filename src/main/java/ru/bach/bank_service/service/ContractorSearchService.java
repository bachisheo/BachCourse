package ru.bach.bank_service.service;


import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_service.dao.ContractorRepository;
import ru.bach.bank_service.entity.Contractor;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

/**
 * Сервис контрагента, отвечающий за операции поиска по наименованию или сочетанию
 * БИК/номер счета.
 */
@Service
@Transactional
public class ContractorSearchService {
    @Autowired
    private ContractorRepository contractorRepository;
    @Autowired
    private MapperFacade mapper;

    /**
     * Поиск контрагента по БИКу и номеру счета
     * /@param bic банковский идентификационный код (БИК)
     * /@param accountNumber номер счета контрагента
     *
     * @return контрагент
     */
    //  Contractor getContractorByBicAndAccountNumber(String bic, String accountNumber);

    /**
     * Получить список всех контрагентов
     *
     * @return список всех контрагентов в БД
     */
    public List<WebContractor> getAll() {
        List<Contractor> actors = contractorRepository.findAll();
        List<WebContractor> webActors = mapper.mapAsList(actors, WebContractor.class);
        System.err.println(webActors);
        return webActors;
    }

    /**
     * Поиск записи о контрагенте по наименованию
     *
     * @param nomination наименование
     * @return запись о контрагенте
     */
    public WebContractor findByNomination(String nomination) {
        Contractor contractor = contractorRepository.findByNomination(nomination);
        WebContractor webContractor = mapper.map(contractor, WebContractor.class);
        return webContractor;
    }

    /**
     * Проверка существования записи в базе данных по наименованию
     *
     * @param nomination наименование
     * @return true если объект существует
     */
    public boolean existsByNomination(String nomination) {
        return contractorRepository.existsByNominationIn(Collections.singletonList(nomination));
    }
}
