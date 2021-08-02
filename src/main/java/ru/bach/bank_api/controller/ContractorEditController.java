package ru.bach.bank_api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.bach.bank_api.model.WebContractor;
import ru.bach.bank_service.service.ContractorEditService;
import ru.bach.bank_service.service.ContractorSearchService;

import javax.validation.Valid;

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
    @ApiOperation(value = "Загрузить форму с добавлением агента",
            notes = "Загружает форму для добавления")

    @GetMapping(path = "/add")
    public ModelAndView showAddActorPage(@ModelAttribute("contractor")
                                                 WebContractor webContractor) {
        return new ModelAndView("add");
    }

    @ApiOperation(value = "Сохранить данные из формы в базу данных",
            notes = "Сохраняет данные из формы в базу данных")
    @PostMapping(value = "/save")
    public ModelAndView save(@ModelAttribute("contractor")
                             @RequestBody @Valid WebContractor webContractor,
                             BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.addObject("contractor", webContractor);
            mv.setViewName("add");
            return mv;
        }
        contractorEditService.save(webContractor);
        mv.setViewName("redirect:/home");
        return mv;
    }

    /**
     * GET-запрос загрузки страницы просмотра контрагента
     * @param nomination наименование
     * @return ModelAndView
     */
    @ApiOperation(value = "Перенаправить на страницу просмотра контрагента")
    @GetMapping("/show/{nomination}")
    public ModelAndView showForm(@PathVariable("nomination")
                                 @Parameter(description = "Наименование контрагента") String nomination) {
        ModelAndView mv = new ModelAndView("show");
        WebContractor webContractor = contractorSearchService.findByNomination(nomination);
        mv.addObject("contractor", webContractor);
        return mv;
    }
    /**
     * GET-запрос загрузки страницы с формой редактирования контрагента
     * @param nomination наименование
     * @return ModelAndView
     */
    @ApiOperation(value = "Перенаправить на страницу с формой для изменения")
    @GetMapping("/edit/{nomination}")
    public ModelAndView showEditActorPage(@PathVariable(name = "nomination") String nomination) {
        ModelAndView mav = new ModelAndView("update");
        WebContractor webContractor = contractorSearchService.findByNomination(nomination);
        mav.addObject("contractor", webContractor);
        return mav;
    }

    /**
     * Пост запрос на сохранение изменений в записи о контрагенте
     * @param webContractor форма с изменениями
     * @return
     */
    @PostMapping(value = "/update")
    public ModelAndView update(@ModelAttribute("contractor") WebContractor webContractor) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/contractors");
        contractorEditService.update(webContractor);
        mav.addObject("statusMessage", "Contractor with nomination " +
                webContractor.getNomination() + "was updated");
        return mav;
    }

    @RequestMapping("/delete/{nomination}")
    public ModelAndView delete(@PathVariable(name = "nomination") String nomination) {
        WebContractor webContractor = contractorSearchService.findByNomination(nomination);
        ModelAndView mav = new ModelAndView("redirect:/contractors");

        contractorEditService.delete(webContractor);
        mav.addObject("statusMessage", "Contractor with nomination " +
                webContractor.getNomination() + "was deleted");
        return mav;
    }
}



