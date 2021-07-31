package ru.bach.bank_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_service.service.ContractorEditService;

/**
 * Контроллер для выполнения запросов на работу с бд
 */

@RestController
public class ContractorEditController {
    @Autowired
    private ContractorEditService contractorEditService;

    /**
     * GET-запрос загрузки формы добавления контрагента
     * @param webContractor объект для заполнения
     * @return модель для отображения страницы
     */
    @GetMapping(path = "/add")
    public ModelAndView createNewContractor(@ModelAttribute("webContractor") WebContractor webContractor) {
        return new ModelAndView("addContractor");
    }

    @PostMapping(value = "/add")
    public ModelAndView add( @ModelAttribute("webContractor")WebContractor webContractor
    ){
        ModelAndView mv = new ModelAndView();
        contractorEditService.save(webContractor);
        mv.setViewName("redirect:/home");
        return mv;
    }



}



