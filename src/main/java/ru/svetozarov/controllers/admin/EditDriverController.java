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
import ru.svetozarov.common.exception.DriverDAOException;
import ru.svetozarov.models.pojo.Auto;
import ru.svetozarov.models.pojo.Driver;
import ru.svetozarov.services.IAutoService;
import ru.svetozarov.services.IDriverService;
import ru.svetozarov.services.IUserService;

import java.util.List;


/**
 * Created by Evgenij on 07.03.2017.
 */

public class EditDriverController {

    private static Logger logger = Logger.getLogger(EditAutoController.class);

    private ru.svetozarov.services.IDriverService IDriverService;
    private ru.svetozarov.services.IAutoService IAutoService;
    @Autowired
    @Qualifier("autoService")
    public void setAutoService(IAutoService IAutoService) {
        this.IAutoService = IAutoService;
    }

    @Autowired
    @Qualifier("driverService")
    public void setDriverService(IDriverService IDriverService) {
        this.IDriverService = IDriverService;
    }

    private ru.svetozarov.services.IUserService IUserService;
    @Autowired
    @Qualifier("userService")
    public void setUserService(IUserService IUserService) {
        this.IUserService = IUserService;
    }


    @RequestMapping(value = "/admin/edit_driver", method = RequestMethod.GET)
    public ModelAndView editDriverGet(@RequestParam(name = "id", defaultValue = "0") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        try {
            Driver driver = IDriverService.getDriverById(id);
            List<Auto> autoList = IAutoService.getAllAuto();
            if (driver != null) {
                logger.trace("Get Driver by id=" + id);
                modelAndView.addObject("driver", driver);
                modelAndView.addObject("listAuto", autoList);
                modelAndView.setViewName("/admin/edit_driver");
            }
        } catch (DriverDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        } catch (AutoDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return  modelAndView;

    }
}
