package ru.bach;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.HttpStatus;

import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_service.dao.ContractorRepository;
import ru.bach.bank_service.service.ContractorEditService;
import ru.bach.bank_service.service.ContractorSearchService;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тестирование сервиса, отвечающего за изменение в базе данных
 */
@SpringBootTest
@AutoConfigureMockMvc
public class EndpointsTest {

    @Autowired
    private MockMvc mvc;
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

    @Test
    @DisplayName("Load page with contractors list")
    void getContractors() throws Exception {
        mvc.perform(get("/contractors")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Post, create new contractor")
    @Transactional
    void createContractor() throws Exception {
        String name = "name" + (int) Math.random()*1000;
        WebContractor counter = WebContractor.builder()
                .id(0L)
                .nomination(name)
                .inn(correctInn[0])
                .kpp(correctKpp[0])
                .accountNumber(correctAccNum[0])
                .bic(correctBic[0])
                .build();

        mvc.perform(post("/save")
                .contentType("application/x-www-form-urlencoded")
                .param("name", counter.getNomination())
                .param("inn", counter.getInn())
                .param("kpp", counter.getKpp())
                .param("accountNumber", counter.getAccountNumber())
                .param("bic", counter.getBic()))
                .andExpect(status().is3xxRedirection());
    }
}