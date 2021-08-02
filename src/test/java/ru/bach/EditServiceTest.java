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
 * Тестирование сервиса, отвечающего за изменение в базе данных
 */
@SpringBootTest
public class EditServiceTest {

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
     * Проверка метода удаления контрагента (по ИД)
     */
    @Test
    @DisplayName("Testing delete method delete(WebContractor)")
    @Transactional
    void testDelete() {
        var actors = initial();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            Assert.assertTrue(searchService.existsByNomination(actors.get(i).getNomination()));
            editService.delete(actors.get(i));
            Assert.assertFalse(searchService.existsByNomination(actors.get(i).getNomination()));
        }
    }

    /**
     * Проверка метода добавления новой записи
     */
    @Test
    @DisplayName("Testing save method save(WebContractor)")
    @Transactional
    void testSave() {
        editService.deleteAll();
        String name = "Name" + (int) (Math.random() * 10000);
        WebContractor actor = WebContractor.builder()
                .inn(correctInn[0])
                .bic(correctBic[0])
                .accountNumber(correctAccNum[0])
                .kpp(correctKpp[0])
                .nomination(name).build();
        Assert.assertFalse(searchService.existsByNomination(actor.getNomination()));
        editService.save(actor);
        Assert.assertTrue(searchService.existsByNomination(actor.getNomination()));
    }

    /**
     * Проверка метода удаления всех записей из таблицы
     */
    @Test
    @DisplayName("Testing delete all records method deleteAll()")
    @Transactional
    void testDeleteAll() {
        var actors = initial();
        Assert.assertEquals(ARRAY_SIZE, searchService.getAll().size());
        editService.deleteAll();
        Assert.assertEquals(0, searchService.getAll().size());
    }

    /**
     * Проверка метода редактирования существующей записи в таблице
     */
    @Test
    @DisplayName("Testing update method Update(WebContractor)")
    @Transactional
    void testUpdate() {
        var actors = initial();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            String oldName = actors.get(i).getNomination();
            String newName = oldName + oldName;
            Assert.assertTrue(searchService.existsByNomination(oldName));
            actors.get(i).setNomination(newName);
            editService.update(actors.get(i));
            Assert.assertFalse(searchService.existsByNomination(oldName));
            Assert.assertTrue(searchService.existsByNomination(newName));
        }
    }

    /**
     * Инициализация массива записей с занесением в БД
     *
     * @return массив моделей записей о контрагентах
     */
    private ArrayList<WebContractor> initial() {
        editService.deleteAll();
        ArrayList<WebContractor> actors = new ArrayList<>(ARRAY_SIZE);
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
