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
 * Created by Evgenij on 08.03.2017.
 */
@Controller
public class EditClientController {

    private static Logger logger = Logger.getLogger(EditAutoController.class);

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

    private ru.svetozarov.services.IUserService IUserService;

    @Autowired
    @Qualifier("userService")
    public void setUserService(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @RequestMapping(value = "/admin/edit_client", method = RequestMethod.GET)
    public ModelAndView editClientGet(@RequestParam(name = "id", defaultValue = "0") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        if (id != 0) {
            try {
                Client client = IClientService.getClientBiId(id);
                if (client != null) {
                    modelAndView.addObject("client", client);
                    modelAndView.setViewName("/admin/edit_client");
                } else {
                    modelAndView.setViewName("redirect:/admin/list_client");
                }
            } catch (ClientDAOException e) {
                logger.error(e);
                modelAndView.setViewName("redirect:/error.jsp");
            }
        } else {
            modelAndView.setViewName("redirect:/admin/list_client");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/edit_client", method = RequestMethod.POST)
    public ModelAndView editClientPost(@RequestParam(name = "login") String login,
                                       @RequestParam(name = "password") String password,
                                       @RequestParam(name = "name") String name,
                                       @RequestParam(name = "sex") String sex,
                                       @RequestParam(name = "phone") String phone,
                                       @RequestParam(name = "email") String email,
                                       @RequestParam(name = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Client tempClient = IClientService.getClientBiId(id);
            String hashPassword;
            if(password.equals("123*/_$")){
                hashPassword = tempClient.getPassword();
            }else hashPassword = IHashPassword.hashingPassword(password);
            Client client = new Client(
                    id,
                    name,
                    sex,
                    phone,
                    email,
                    login,
                    hashPassword
            );

            if (IUserService.checkUserByLoginAndId(client.getLogin(), client.getId())) {
                if (IClientService.updateClient(client)) {
                    logger.trace("Client " + client.getName() + " updated");
                    modelAndView.setViewName("redirect:/admin/list_client");
                } else {
                    logger.trace("Dublicate client");
                    modelAndView.setViewName("redirect:/admin/edit_client?id=" + client.getId());
                }
            } else {
                modelAndView.setViewName("redirect:/admin/edit_client?id=" + client.getId());
            }
        }catch (HashPasswordException e){
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        catch (UserDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        } catch (ClientDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }
}
