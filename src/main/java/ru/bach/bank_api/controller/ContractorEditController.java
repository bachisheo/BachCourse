package ru.bach.bank_api.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_service.service.ContractorEditService;
import ru.bach.bank_api.service.ContractorSearchService;

/**
 * Контроллер для выполнения запросов на работу с бд
 */

@RestController
public class ContractorEditController {
    @Autowired
    private ContractorEditService contractorEditService;
    @Autowired
    private ContractorSearchService contractorSearchService;

    /**
     * GET-запрос загрузки формы добавления контрагента
     *
     * @param webContractor объект для заполнения
     * @return модель для отображения страницы
     */
    @GetMapping(path = "/add")
    public ModelAndView showAddActorPage(@ModelAttribute("contractor") WebContractor webContractor) {
        return new ModelAndView("add");
    }

    @PostMapping(value = "/save")
    public ModelAndView save(@ModelAttribute("contractor") WebContractor webContractor) {
        ModelAndView mv = new ModelAndView();
        contractorEditService.save(webContractor);
        mv.setViewName("redirect:/home");
        return mv;
    }

    @GetMapping("/show/{nomination}")
    public ModelAndView showForm(@PathVariable("nomination")
                                     @Parameter(description = "Наименование контрагента") String nomination) {
        ModelAndView mv = new ModelAndView("show");
        WebContractor webContractor = contractorSearchService.findByNomination(nomination);
        mv.addObject("contractor", webContractor);

        return mv;
    }

    @GetMapping("/edit/{nomination}")
    public ModelAndView showEditActorPage(@PathVariable(name = "nomination") String nomination) {
        ModelAndView mav = new ModelAndView("update");
        WebContractor webContractor = contractorSearchService.findByNomination(nomination);
        mav.addObject("contractor", webContractor);
        return mav;
    }

    @PostMapping(value = "/update")
    public ModelAndView update(@ModelAttribute("contractor") WebContractor webContractor) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/contractors");

       contractorEditService.update(webContractor);
//        mav.addObject("statusMessage", "Contractor with nomination " +
//                webContractor.getNomination() + "was updated");
        return mav;
    }
}



