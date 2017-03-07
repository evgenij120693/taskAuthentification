package ru.svetozarov.controllers.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.DriverDAOException;
import ru.svetozarov.models.pojo.Driver;
import ru.svetozarov.services.IDriverService;

import java.util.List;

/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class ListDriverController {
    private static Logger logger = Logger.getLogger(ListDriverController.class);

    private ru.svetozarov.services.IDriverService IDriverService;
    @Autowired
    @Qualifier("driverService")
    public void setDriverService(IDriverService IDriverService) {
        this.IDriverService = IDriverService;
    }

    @RequestMapping(name = "/admin/list_driver", method = RequestMethod.GET)
    public ModelAndView listDriverGet(){
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Driver> list = IDriverService.getListDriver();
            modelAndView.addObject("list", list);
            modelAndView.setViewName("/admin/list_driver");
        } catch (DriverDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }
}
