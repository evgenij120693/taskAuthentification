package ru.svetozarov.controllers.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.DriverDAOException;
import ru.svetozarov.services.IDriverService;

/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class DeleteDriverController {

    private static Logger logger = Logger.getLogger(DeleteDriverController.class);

    private ru.svetozarov.services.IDriverService IDriverService;
    @Autowired
    @Qualifier("driverService")
    public void setDriverService(IDriverService IDriverService) {
        this.IDriverService = IDriverService;
    }

    @RequestMapping(value = "/admin/delete_driver", method = RequestMethod.GET)
    public String deleteDriverGet(@RequestParam(name = "id", defaultValue = "0") Integer id){
        if(id != 0){
            try {
                IDriverService.deleteDriverById(id);
                logger.trace("delete driver by id="+id+" successfull");
                return "redirect:/admin/list_driver";
            } catch (DriverDAOException e) {
                logger.error(e);
                return "redirect:/error.jsp";
            }

        }else {
            logger.trace("Id is null");
            return "redirect:/admin/list_driver";
        }
    }
}
