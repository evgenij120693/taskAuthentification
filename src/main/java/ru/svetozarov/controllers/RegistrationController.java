package ru.svetozarov.controllers;

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
import ru.svetozarov.common.util.IHashPassword;
import ru.svetozarov.models.pojo.Client;
import ru.svetozarov.services.IClientService;
import ru.svetozarov.services.IUserService;

/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class RegistrationController {

    private static Logger logger = Logger.getLogger(RegistrationController.class);
    private ru.svetozarov.services.IClientService IClientService;
    @Autowired
    @Qualifier("clientService")
    public void setClientService(IClientService IClientService) {
        this.IClientService = IClientService;
    }

    private ru.svetozarov.common.util.IHashPassword IHashPassword;
    @Autowired
    @Qualifier("hashPassword")
    public void setHashPassword(IHashPassword IHashPassword) {
        this.IHashPassword = IHashPassword;
    }

    private ru.svetozarov.services.IUserService IUserService;
    @Autowired
    @Qualifier("userService")
    public void setUserService(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationGet(){
        return "/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registrationPost(@RequestParam(name = "login") String login,
                                         @RequestParam(name = "password") String password,
                                         @RequestParam(name = "name") String name,
                                         @RequestParam(name = "sex") String sex,
                                         @RequestParam(name = "phone") String phone,
                                         @RequestParam(name = "email") String email){
        ModelAndView modelAndView = new ModelAndView();
        try {
            password = IHashPassword.hashingPassword(password);

            String role = "client";
            String greetings = "";
            Client client = new Client(
                    0,
                    name,
                    sex,
                    phone,
                    email,
                    login,
                    password
            );

            if (IUserService.checkUserByLogin(client.getLogin())) {
                if (IClientService.addClient(client)) {
                    logger.trace("Client " + client.getName() + " added");
                    modelAndView.setViewName("redirect:/login");
                } else {
                    logger.trace("Dublicate client");
                    //resp.sendRedirect("/taxi/registration");
                    modelAndView.addObject("error", "Произошла ошибка попробуйте позже.");
                    modelAndView.addObject("/registration");
                }
            } else {
                modelAndView.addObject("error", "Пользователь с таким логином уже существует.");
                modelAndView.setViewName("/registration");
                //resp.sendRedirect("/taxi/registration");
            }
        } catch (UserDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        } catch (ClientDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        } catch (HashPasswordException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }

}
