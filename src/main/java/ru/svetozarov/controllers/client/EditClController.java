package ru.svetozarov.controllers.client;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.common.exception.HashPasswordException;
import ru.svetozarov.models.pojo.Client;
import ru.svetozarov.services.IClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Evgenij on 08.03.2017.
 */
@Controller
public class EditClController {
    private static Logger logger = Logger.getLogger(EditClController.class);

    private ru.svetozarov.common.util.IHashPassword IHashPassword;

    @Autowired
    @Qualifier("hashPassword")
    public void setHashPassword(ru.svetozarov.common.util.IHashPassword IHashPassword) {
        this.IHashPassword = IHashPassword;
    }

    private ru.svetozarov.services.IClientService IClientService;

    @Autowired
    @Qualifier("clientService")
    public void setClientService(IClientService IClientService) {
        this.IClientService = IClientService;
    }

    @RequestMapping(value = "/client/edit", method = RequestMethod.GET)
    public ModelAndView editClientGet(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        if (id != 0) {
            try {
                Client client = IClientService.getClientBiId(id);
                if (client != null) {
                    req.setAttribute("client", client);
                    modelAndView.setViewName("/client/edit");
                } else {
                    modelAndView.setViewName("redirect:/client/");
                }
            } catch (ClientDAOException e) {
                logger.error(e);
                modelAndView.setViewName("redirect:/error.jsp");
            }
        } else {
            modelAndView.setViewName("redirect:/client");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/client/edit", method = RequestMethod.POST)
    public ModelAndView editClientPost(HttpServletRequest req,
                                       @RequestParam(name = "name") String name,
                                       @RequestParam(name = "password") String password,
                                       @RequestParam(name = "sex") String sex,
                                       @RequestParam(name = "phone") String phone,
                                       @RequestParam(name = "email") String email) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");

        try {
            String hashPassword;

            Client temp = IClientService.getClientBiId(id);
            if (password.equals("32.,5/*/")) {
                hashPassword = temp.getPassword();
            } else {
                hashPassword = IHashPassword.hashingPassword(password);
            }
            Client client = new Client(
                    id,
                    name,
                    sex,
                    phone,
                    email,
                    temp.getLogin(),
                    hashPassword
            );

            if (IClientService.updateClient(client)) {
                logger.trace("Client " + client.getName() + " updated");
                modelAndView.setViewName("redirect:/client");
            } else {
                logger.trace("Error update client");
                modelAndView.setViewName("redirect:/client/edit");
            }
        } catch (HashPasswordException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        } catch (ClientDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }
}
