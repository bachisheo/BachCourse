package ru.bach.bank_api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_service.service.ContractorSearchService;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для выполнения запросов на
 * поисковую работу с бд
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

    /**
     * * GET-запрос загрузки главной страницы
     *
     * @return модель для отображения страницы
     */
    @RequestMapping(path = "/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    /**
     * Get-запрос загрузки страницы поиска
     * @return модель для отображения страницы
     */
    @GetMapping(path ="/searchByName")
    public ModelAndView openSearchPage(){
        WebContractor actor = WebContractor.builder().nomination("Введите наименование").build();
        ModelAndView mav = new ModelAndView("/searchByName");
        mav.addObject("contractor", actor);
        return mav;
    }
    @PostMapping(path ="/searchByName")
    public ModelAndView search(@ModelAttribute("contractor")
                                           WebContractor webContractor){
        ModelAndView mav = new ModelAndView("/searchByName");
        List<WebContractor> agentList = contractorSearchService.finderByNomination(webContractor.getNomination());
        mav.addObject("contractorsFromServer", agentList);
        return mav;
    }
    /**
     * GET-запрос загрузки страницы просмотра контрагента
     *
     * @param nomination наименование
     * @return ModelAndView
     */
    @ApiOperation(value = "Перенаправить на страницу просмотра контрагента")
    @GetMapping("/show/{nomination}")
    public ModelAndView showForm(@PathVariable("nomination")
                                 @Parameter(description = "Наименование контрагента") String nomination) {
        ModelAndView mav = new ModelAndView("/show");
        WebContractor webContractor = contractorSearchService.findByNomination(nomination);
        if (webContractor == null)
            return new ModelAndView("redirect:/error");
        mav.addObject("contractor", webContractor);
        return mav;
    }
}



