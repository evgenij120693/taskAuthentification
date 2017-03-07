package ru.svetozarov.controllers.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.AutoDAOException;
import ru.svetozarov.models.pojo.Auto;
import ru.svetozarov.services.IAutoService;

/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class AddAutoController {
    private static Logger logger = Logger.getLogger(AddAutoController.class);

    private ru.svetozarov.services.IAutoService IAutoService;
    @Autowired
    @Qualifier("autoService")
    public void setAutoService(IAutoService IAutoService) {
        this.IAutoService = IAutoService;
    }

    @RequestMapping(value = "/admin/add_auto", method = RequestMethod.GET)
    public String addAutoGet(){
        return "/admin/add_auto";
    }

    @RequestMapping(value = "/admin/add_auto", method = RequestMethod.POST)
    public ModelAndView addAutoPost(@RequestParam(name = "marka") String marka,
                                    @RequestParam(name = "model") String model,
                                    @RequestParam(name = "regNumber") String regNUmber,
                                    @RequestParam(name = "color") String color){
        ModelAndView modelAndView = new ModelAndView();
        Auto auto = new Auto(
                0,
                marka,
                model,
                regNUmber,
                color
        );
        try {
            if(IAutoService.addAuto(auto)){
                logger.trace("adding successful ");
                modelAndView.setViewName("redirect:/admin/list_auto");
            }else{
                logger.trace("adding failed ");
                modelAndView.setViewName("redirect:/admin/list_auto");
            }
        } catch (AutoDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }
}
