package ru.svetozarov.controllers.admin;

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
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.pojo.Client;
import ru.svetozarov.services.IClientService;
import ru.svetozarov.services.IUserService;

/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class AddClientController {

    private static Logger logger = Logger.getLogger(AddClientController.class);

    private ru.svetozarov.common.util.IHashPassword IHashPassword;

    @Autowired
    @Qualifier("hashPassword")
    public void setHashPassword(ru.svetozarov.common.util.IHashPassword IHashPassword) {
        this.IHashPassword = IHashPassword;
    }

    private ru.svetozarov.services.IUserService IUserService;

    @Autowired
    @Qualifier("userService")
    public void setUserService(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    private ru.svetozarov.services.IClientService IClientService;

    @Autowired
    @Qualifier("clientService")
    public void setClientService(IClientService IClientService) {
        this.IClientService = IClientService;
    }


    @RequestMapping(value = "/admin/add_client", method = RequestMethod.GET)
    public ModelAndView addClientGet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/add_client");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/add_client", method = RequestMethod.POST)
    public ModelAndView addClientPost(@RequestParam(name = "login") String login,
                                      @RequestParam(name = "password") String password,
                                      @RequestParam(name = "name") String name,
                                      @RequestParam(name = "sex") String sex,
                                      @RequestParam(name = "phone") String phone,
                                      @RequestParam(name = "email") String email) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Client client = new Client(
                    0,
                    name,
                    sex,
                    phone,
                    email,
                    login,
                    IHashPassword.hashingPassword(password)
            );
            if (IUserService.checkUserByLogin(client.getLogin())) {
                if (IClientService.addClient(client)) {
                    logger.trace("Client " + client.getName() + " added");
                    modelAndView.setViewName("redirect:/admin/list_client");
                } else {
                    logger.trace("Dublicate client");
                    modelAndView.setViewName("redirect:/admin/add_client");
                }
            } else {
                modelAndView.setViewName("redirect:/admin/add_client");
            }
        }catch (HashPasswordException e){
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }catch (UserDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        } catch (ClientDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;

    }
}
