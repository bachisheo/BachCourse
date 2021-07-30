package ru.bach.bank_service.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_service.entity.Contractor;
import ru.bach.bank_service.service.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Контроллер для выполнения запросов на работу с бд
 */

@RestController
public class ContractorController {
    @Autowired
    private ContractorEditService contractorEditService;
    private ContractorSearchService contractorSearchService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Contractor greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Contractor cont = new Contractor();
        cont.setNomination(name);
        return cont;
    }
    /**
     * GET-запрос загрузки страницы контрагентов
     * @param model модель страницы
     * @return модель для отображения конечной страницы с переданной информацией
     */
//    @ApiOperation(value = "Загрузка страницы со списком контрагентов",
//            notes = "Этот метод загружает страницы с таблицей контрагентов")
//    @GetMapping("/contractors")
//    public ModelAndView getAllUsers(Model model) {
//        List<WebContractor> agentList = contractorSearchService.getAll();
//        model.addAttribute("counterAgentsFromServer", agentList);
//        model.addAttribute("deleteAgent", new WebContractor());
//        return new ModelAndView("/contractors");
//    }

}



