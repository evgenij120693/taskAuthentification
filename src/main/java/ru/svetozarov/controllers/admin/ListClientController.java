package ru.svetozarov.controllers.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.models.pojo.Client;
import ru.svetozarov.services.IClientService;

import java.util.List;

/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class ListClientController {
    private static Logger logger = Logger.getLogger(ListClientController.class);

    private ru.svetozarov.services.IClientService IClientService;
    @Autowired
    @Qualifier("clientService")
    public void setClientService(IClientService IClientService) {
        this.IClientService = IClientService;
    }

    @RequestMapping(value = "/admin/list_client", method = RequestMethod.GET )
    public ModelAndView listClientGet(){
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Client> list = IClientService.getAllClients();
           modelAndView.addObject("list", list);
            modelAndView.setViewName("/admin/list_client");
        } catch (ClientDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }

}
