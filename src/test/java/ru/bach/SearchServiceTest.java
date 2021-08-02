package ru.bach;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_service.service.ContractorSearchService;
import ru.bach.bank_service.dao.ContractorRepository;
import ru.bach.bank_service.service.ContractorEditService;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Тестирование сервиса, отвечающего за получение данных из базы
 */
@SpringBootTest
public class SearchServiceTest {

    @Autowired
    private ContractorRepository repository;
    @Autowired
    private ContractorSearchService searchService;
    @Autowired
    private ContractorEditService editService;
    private static final int ARRAY_SIZE = 20;
    private String[] correctInn = {"7743013901"};
    private String[] correctBic = {"049805716"};
    private String[] correctAccNum = {"40602810800000000025"};
    private String[] correctKpp = {"123456789"};

    /**
     * Проверка метода получения всех записей из БД
     */
    @Test
    @DisplayName("Testing search method getAll()")
    @Transactional
    void testFindAll() {
        var actors = initial();
        var actorsFromDB = searchService.getAll();
        for (int i = 0; i < ARRAY_SIZE; i++)
            Assert.assertTrue(actorsFromDB.contains(actors.get(i)));
    }

    /**
     * Проверка метода поиска записи по наименованию
     */
    @Test
    @DisplayName("Testing search method findByNomination()")
    @Transactional
    void testFindByName() {
        var actors = initial();

        var actorsFromDB = searchService.getAll();

        for (int i = 0; i < ARRAY_SIZE; i++)
            Assert.assertEquals(actors.get(i), searchService.findByNomination(actors.get(i).getNomination()));
    }

    /**
     * Инициализация массива записей с занесением в БД
     *
     * @return массив моделей записей о контрагентах
     */
    private ArrayList<WebContractor> initial() {
        editService.deleteAll();
        ArrayList<WebContractor> actors = new ArrayList<>(ARRAY_SIZE);
        String[] correctInn = {"7743013901"};
        String[] correctBic = {"049805716"};
        String[] correctAccNum = {"40602810800000000025"};
        String[] correctKpp = {"123456789"};
        for (int i = 0; i < ARRAY_SIZE; i++) {
            WebContractor actor = WebContractor.builder()
                    .inn(correctInn[0])
                    .bic(correctBic[0])
                    .accountNumber(correctAccNum[0])
                    .kpp(correctKpp[0])
                    .nomination("Name" + i).build();
            actors.add(actor);
            editService.save(actor);
        }
        return (ArrayList<WebContractor>) searchService.getAll();
    }
}
