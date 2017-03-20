package ru.svetozarov.controllers.driver;

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

import javax.servlet.http.HttpSession;

/**
 * Created by Evgenij on 08.03.2017.
 */
@Controller
public class DriverAccountController {
    private static Logger logger = Logger.getLogger(DriverAccountController.class);

    private ru.svetozarov.services.IDriverService IDriverService;
    @Autowired
    @Qualifier("driverService")
    public void setDriverService(IDriverService IDriverService) {
        this.IDriverService = IDriverService;
    }

    @RequestMapping(value = "/driver", method = RequestMethod.GET)
    public ModelAndView driverAccountGet(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        int id = (int) session.getAttribute("id");
        if (id != 0) {
            try {
                Driver driver = IDriverService.getDriverById(id);
                if (driver != null) {
                    modelAndView.addObject("driver", driver);
                    modelAndView.setViewName("/driver/index");
                } else {
                    modelAndView.setViewName("redirect:/driver");
                }
            } catch (DriverDAOException e) {
                logger.error(e);
                modelAndView.setViewName("redirect:/error.jsp");
            }
        } else {
            modelAndView.setViewName("redirect:/driver");
        }
        modelAndView.setViewName("/driver/index");
        return  modelAndView;
    }
}
