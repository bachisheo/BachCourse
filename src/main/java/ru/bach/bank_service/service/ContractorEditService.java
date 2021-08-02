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
     * Добавление записи о контрагенте
     *
     * @param webContractor канальная модель контрагента
     */
    @Transactional
    public void save(WebContractor webContractor) {
        if (contractorRepository.existsByNominationIn(Collections.singletonList(webContractor.getNomination()))) {
            throw new DuplicateException("Contractor with nomination "
                    + webContractor.getNomination() + " already exists");
        }
        Contractor contractor = mapper.map(webContractor, Contractor.class);
        contractorRepository.save(contractor);
    }

    /**
     * Удаление записи о контрагенте по id
     *
     * @param webContractor канальная модель контрагента
     */
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

    /**
     * Удаление всех записей о контрагентах
     */
    @Transactional
    public void deleteAll() {
        contractorRepository.deleteAll();
    }

    /**
     * Обновление записи об контрагенте
     *
     * @param webContractor канальная модель контрагента
     */
    @Transactional
    public void update(WebContractor webContractor) {
        Contractor contractor = mapper.map(webContractor, Contractor.class);
        if (!contractorRepository.existsById(contractor.getId())) {
            throw new ContractorNotFoundException("Contractor with nomination " + contractor.getNomination() + "id"
                    + contractor.getId()
                    + " does not exists");
        }
        contractorRepository.save(contractor);
    }
}
