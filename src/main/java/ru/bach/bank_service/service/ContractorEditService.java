package ru.bach.bank_service.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_service.dao.ContractorRepository;
import ru.bach.bank_service.entity.Contractor;
import ru.bach.bank_service.exception.ContractorNotFoundException;
import ru.bach.bank_service.exception.DuplicateException;

import javax.transaction.Transactional;
import java.util.Collections;

/**
 * Сервис контрагента, отвечающий за операции удаления/добавления/изменения.
 */
@Service
@Transactional
public class ContractorEditService {
    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private MapperFacade mapper;

    /**
     * Проверка существования записи в базе данных по наименованию
     *
     * @param nomination наименование
     * @return true если объект существует
     */
    public boolean existsByNomination(String nomination){
        return contractorRepository.existsByNominationIn(Collections.singletonList(nomination));
    }
    /**
     * Сохранение записи о контрагенте
     *
     * @param webContractor контрагент
     */
    @Transactional
    public Contractor save(WebContractor webContractor) {
        if (existsByNomination(webContractor.getNomination())){
            throw new DuplicateException("Contractor with nomination " + webContractor.getNomination() + " already exists");
        }
        Contractor contractor = mapper.map(webContractor, Contractor.class);
        return contractorRepository.save(contractor);
    }



    @Transactional
    public void delete(WebContractor webContractor) {
        Contractor contractor = mapper.map(webContractor, Contractor.class);
        if (!contractorRepository.existsById(contractor.getId())) {
            throw new ContractorNotFoundException("Contractor with nomination " + contractor.getNomination() + "id"
                    + contractor.getId()
                    + " does not exists");
        }
        contractorRepository.deleteById(contractor.getId());
    }

    @Transactional
    public Contractor update(WebContractor webContractor) {
        Contractor contractor = mapper.map(webContractor, Contractor.class);
        if (!contractorRepository.existsById(contractor.getId())) {
            throw new ContractorNotFoundException("Contractor with nomination " + contractor.getNomination() + "id"
                    + contractor.getId()
                    + " does not exists");
        }
        return contractorRepository.save(contractor);
    }


}
