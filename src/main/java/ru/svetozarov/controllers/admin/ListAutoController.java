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

import java.util.List;


/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class ListAutoController {
    private static Logger logger = Logger.getLogger(ListAutoController.class);

    private ru.svetozarov.services.IAutoService IAutoService;
    @Autowired
    @Qualifier("autoService")
    public void setAutoService(IAutoService IAutoService) {
        this.IAutoService = IAutoService;
    }

    @RequestMapping(value = "/admin/list_auto", method = RequestMethod.GET)
    public ModelAndView listAutoGet(){
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Auto> list = IAutoService.getAllAuto();
            logger.trace("get all auto count="+list.size());
           modelAndView.addObject("list", list);
            modelAndView.setViewName("/admin/list_auto");
        } catch (AutoDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }
}
