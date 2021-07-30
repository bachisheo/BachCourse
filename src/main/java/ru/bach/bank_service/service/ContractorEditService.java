package ru.bach.bank_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bach.bank_service.dao.ContractorRepository;
import ru.bach.bank_service.entity.Contractor;

import javax.transaction.Transactional;
import java.util.Collections;

/**
 * Сервис контрагента, отвечающий за операции удаления/добавления/изменения.
 */
@Service
public class ContractorEditService {
    @Autowired
    private ContractorRepository contractorRepository;

    /**
     * Сохранение записи о контрагенте
     * @param contractor контрагент
     */
    @Transactional
    public Contractor save(Contractor contractor) {
        if (contractorRepository.existsByNominationIn(Collections.singletonList(contractor.getNomination()))) {
            //throw new DuplicateException("Contractor with nomination " + contractor.getNomination() + " already exists");
        }
        return contractorRepository.save(contractor);
    }




}
