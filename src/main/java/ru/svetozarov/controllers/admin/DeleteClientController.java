package ru.svetozarov.controllers.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.services.IClientService;

/**
 * Created by Evgenij on 08.03.2017.
 */
@Controller
public class DeleteClientController {
    private static Logger logger = Logger.getLogger(DeleteClientController.class);

    private ru.svetozarov.services.IClientService IClientService;
    @Autowired
    @Qualifier("clientService")
    public void setClientService(IClientService IClientService) {
        this.IClientService = IClientService;
    }

    @RequestMapping(value = "/admin/delete_client")
    public String deleteClientGet(@RequestParam(name = "id") Integer id){
        if(id != 0){
            try {
                if(IClientService.deleteClientById(id)){
                    return "redirect:/admin/list_client";
                }
            } catch (ClientDAOException e) {
                logger.error(e);
                return "redirect:/error.jsp";
            }
        }
        return "redirect:list_client";

    }
}
