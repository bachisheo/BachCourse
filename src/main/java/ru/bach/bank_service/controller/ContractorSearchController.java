package ru.bach.bank_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_service.service.ContractorSearchService;

import java.util.List;

/**
 * Контроллер для выполнения запросов на работу с бд
 */

@RestController
public class ContractorSearchController {

    @Autowired
    private ContractorSearchService contractorSearchService;

    /**
     * GET-запрос загрузки страницы контрагентов
     *
     * @return модель для отображения страницы
     */
    @RequestMapping(path = "/contractors")
    public ModelAndView getAllContractors() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contractors");
        List<WebContractor> agentList = contractorSearchService.getAll();
        mv.addObject("contractorsFromServer", agentList);
        return mv;
    }

    @RequestMapping(path = "/home")
    public ModelAndView home(@ModelAttribute("webContractor") WebContractor webContractor) {
        return new ModelAndView("home");
    }
}



