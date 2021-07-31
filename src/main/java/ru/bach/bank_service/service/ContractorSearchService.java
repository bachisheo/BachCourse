package ru.bach.bank_service.service;


import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_service.dao.ContractorRepository;
import ru.bach.bank_service.entity.Contractor;

import java.util.List;

/**
 * Сервис контрагента, отвечающий за операции поиска по наименованию или сочетанию
 * БИК/номер счета.
 */
@Service
public class ContractorSearchService {
    @Autowired
    private ContractorRepository contractorRepository;
    @Autowired
   private MapperFacade mapper;
    /**
     * Поиск контрагента по наименованию
     * @param nomination наименование
     * @return контрагент
     */
   // Contractor getContractorByNomination(String nomination);

    /**
     * Поиск контрагента по БИКу и номеру счета
     * /@param bic банковский идентификационный код (БИК)
     * /@param accountNumber номер счета контрагента
     * @return контрагент
     */
  //  Contractor getContractorByBicAndAccountNumber(String bic, String accountNumber);

    public List<WebContractor> getAll(){
        List<Contractor> actors = contractorRepository.findAll();
        List<WebContractor> webActors = mapper.mapAsList(actors, WebContractor.class);
        System.err.println(webActors);
        return webActors;
    }
public WebContractor getOne(){
        Contractor c = new Contractor();
        c.setNomination("contr");
        WebContractor wc = mapper.map(c, WebContractor.class);
        return wc;
}

}
