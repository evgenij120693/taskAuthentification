package ru.svetozarov.controllers.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.svetozarov.common.exception.AutoDAOException;
import ru.svetozarov.services.IAutoService;

/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class DeleteAutoController {
    private static Logger logger = Logger.getLogger(DeleteAutoController.class);

    private ru.svetozarov.services.IAutoService IAutoService;
    @Autowired
    @Qualifier("autoService")
    public void setAutoService(IAutoService IAutoService) {
        this.IAutoService = IAutoService;
    }

    @RequestMapping(value = "/admin/delete_auto")
    public String deleteAutoController(@RequestParam(name = "id", defaultValue = "0") Integer id){
        if(id != 0){
            try {
                if(IAutoService.deleteAuto(id)){
                    logger.trace("Delete auto by id="+id+" successful");
                    return "redirect:/admin/list_auto";
                }else{
                    logger.trace("Delete auto by id="+id+" failed");
                    return "redirect:/admin/list_auto";
                }
            } catch (AutoDAOException e) {
                logger.error(e);
                return "redirect:/error.jsp";
            }
        }
        return "redirect:/admin/list_auto";
    }
}
