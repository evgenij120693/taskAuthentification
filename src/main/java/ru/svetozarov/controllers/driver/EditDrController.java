package ru.svetozarov.controllers.driver;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.DriverDAOException;
import ru.svetozarov.common.exception.HashPasswordException;
import ru.svetozarov.common.exception.StatusDAOException;
import ru.svetozarov.common.util.IHashPassword;
import ru.svetozarov.models.pojo.Driver;
import ru.svetozarov.models.pojo.Status;
import ru.svetozarov.services.IDriverService;
import ru.svetozarov.services.IStatusService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Evgenij on 08.03.2017.
 */
@Controller
public class EditDrController {
    private static Logger logger = Logger.getLogger(EditDrController.class);

    private ru.svetozarov.common.util.IHashPassword iHashPassword;

    @Autowired
    @Qualifier("hashPassword")
    public void setHashPassword(ru.svetozarov.common.util.IHashPassword IHashPassword) {
        this.iHashPassword = IHashPassword;
    }

    private ru.svetozarov.services.IDriverService IDriverService;
    @Autowired
    @Qualifier("driverService")
    public void setDriverService(IDriverService IDriverService) {
        this.IDriverService = IDriverService;
    }

    private ru.svetozarov.services.IStatusService IStatusService;
    @Autowired
    @Qualifier("statusService")
    public void setStatusService(IStatusService IStatusService) {
        this.IStatusService = IStatusService;
    }


    @RequestMapping(value = "/driver/edit", method = RequestMethod.GET)
    public ModelAndView editDriverGet(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        int id = (int) session.getAttribute("id");
        if (id != 0) {
            try {
                Driver driver = IDriverService.getDriverByIdJoinAutoAndStatus(id);
                List<Status> statusList = IStatusService.getAllStatusDriver();
                if (driver != null) {
                    modelAndView.addObject("driver", driver);
                    modelAndView.addObject("statusList", statusList);
                    modelAndView.setViewName("/driver/edit");
                } else {
                    modelAndView.setViewName("redirect:/driver/");
                }
            } catch (DriverDAOException e) {
                logger.error(e);
                modelAndView.setViewName("redirect:/error.jsp");
            } catch (StatusDAOException e) {
                logger.error(e);
                modelAndView.setViewName("redirect:/error.jsp");
            }
        } else {
            modelAndView.setViewName("redirect:/driver");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/driver/edit", method = RequestMethod.POST)
    public ModelAndView editDriverPost(HttpSession session,
                                       @RequestParam(name = "lastName") String lastName,
                                       @RequestParam(name = "firstName") String firstName,
                                       @RequestParam(name = "phone") String phone,
                                       @RequestParam(name = "password") String password){
        ModelAndView modelAndView = new ModelAndView();
        int id = (int) session.getAttribute("id");
        try {
            Driver temp = IDriverService.getDriverById(id);
            String hashPassword;
            if(password.equals("/*-1235-**")){
                hashPassword = temp.getPassword();
            }else hashPassword = iHashPassword.hashingPassword(password);
            Driver driver = new Driver(
                    id,
                    lastName,
                    firstName,
                    phone,
                    temp.getLogin(),
                    hashPassword,
                    temp.getRating(),
                    temp.getAuto(),
                    temp.getStatus()
            );
            if(IDriverService.updateDriver(driver)){
                logger.trace("Update driver successful");
                modelAndView.setViewName("redirect:/driver");
            }else{
                logger.trace("Update driver failed");
                modelAndView.setViewName("redirect:/driver/edit");
            }
        }catch (HashPasswordException e){
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        catch (DriverDAOException e){
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }
}
