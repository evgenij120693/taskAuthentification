package ru.svetozarov.controllers.client;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Evgenij on 08.03.2017.
 */
@Controller
public class ClientAccountConntroller {
    private static Logger logger = Logger.getLogger(ClientAccountConntroller.class);

    private ru.svetozarov.services.IClientService IClientService;
    @Autowired
    @Qualifier("clientService")
    public void setClientService(IClientService IClientService) {
        this.IClientService = IClientService;
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ModelAndView clientAccountGet(HttpServletRequest req){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        if (id != 0) {
            try {
                Client client = IClientService.getClientBiId(id);
                if (client != null) {
                    modelAndView.addObject("client", client);
                    modelAndView.setViewName("/client/index");
                } else {
                    modelAndView.setViewName("redirect:/client/");
                }
            } catch (ClientDAOException e) {
                logger.error(e);
                modelAndView.setViewName("redirect:/error.jsp");
            }
        } else {
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return  modelAndView;
    }
}
