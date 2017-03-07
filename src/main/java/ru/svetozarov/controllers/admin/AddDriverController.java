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
import ru.svetozarov.common.exception.HashPasswordException;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.common.util.IHashPassword;
import ru.svetozarov.models.pojo.Auto;
import ru.svetozarov.models.pojo.Driver;
import ru.svetozarov.services.IAutoService;
import ru.svetozarov.services.IDriverService;
import ru.svetozarov.services.IUserService;

import java.util.List;

/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class AddDriverController {
    private static Logger logger = Logger.getLogger(AddDriverController.class);

    private ru.svetozarov.common.util.IHashPassword IHashPassword;

    @Autowired
    @Qualifier("hashPassword")
    public void setHashPassword(ru.svetozarov.common.util.IHashPassword IHashPassword) {
        this.IHashPassword = IHashPassword;
    }

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

    @RequestMapping(value = "/admin/add_driver", method = RequestMethod.GET)
    public ModelAndView addDiverGet() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Auto> autoList = IAutoService.getAllAuto();
            modelAndView.addObject("listAuto", autoList);
            modelAndView.setViewName("/admin/add_driver");
        } catch (AutoDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/add_driver", method = RequestMethod.POST)
    public ModelAndView addDriverPost(@RequestParam(name = "login") String login,
                                      @RequestParam(name = "password") String password,
                                      @RequestParam(name = "auto") Integer auto,
                                      @RequestParam(name = "rating") Integer rating,
                                      @RequestParam(name = "lastName") String lastName,
                                      @RequestParam(name = "firstName") String firstName,
                                      @RequestParam(name = "phone") String phone) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            String hashPassword = IHashPassword.hashingPassword(password);
            Driver driver = new Driver(
                    0,
                    lastName,
                    firstName,
                    phone,
                    login,
                    hashPassword,
                    rating,
                    auto,
                    1
            );

            if (IUserService.checkUserByLogin(login)) {
                if (IDriverService.addDriver(driver)) {
                    logger.trace("Driver added successful");
                    modelAndView.setViewName("redirect:/admin/list_driver");
                } else {
                    logger.trace("Driver add failed");
                    modelAndView.setViewName("redirect:/admin/list_driver");
                }
            } else {
                logger.trace("Login dublicated!!");
                modelAndView.setViewName("redirect:/admin/list_driver");
            }
        } catch (HashPasswordException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        } catch (UserDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        } catch (DriverDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }
}
