package ru.svetozarov.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.HashPasswordException;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.common.util.IHashPassword;
import ru.svetozarov.models.pojo.User;
import ru.svetozarov.services.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class AuthotizationController {
    private static Logger logger = Logger.getLogger(AuthotizationController.class);

    private ru.svetozarov.services.IUserService IUserService;
    private ru.svetozarov.common.util.IHashPassword IHashPassword;
    @Autowired
    @Qualifier("hashPassword")
    public void setHashPassword(IHashPassword IHashPassword) {
        this.IHashPassword = IHashPassword;
    }

    @Autowired
    @Qualifier("userService")
    public void setUserService(IUserService IUserService) {
        this.IUserService = IUserService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(){
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost(HttpServletRequest request,
                                  @RequestParam(name = "login") String login,
                                  @RequestParam(name = "password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = null;
            user = IUserService.getUserByLoginAndPassword(login, IHashPassword.hashingPassword(password));

            if (user != null) {
                setSession(user.getId(), user.getRole(), user.getName(), request);
                modelAndView.setViewName("redirect:/" + user.getRole());
            } else {
                logger.trace("Authorization failed");
                modelAndView.addObject("error", "Неверный логин/пароль.");
                modelAndView.setViewName("/login");
                //resp.sendRedirect("/taxi/login");
            }
        } catch (UserDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        } catch (HashPasswordException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }

    private static void setSession(int id, String role, String name, HttpServletRequest req){
        HttpSession session = req.getSession();
        session.setAttribute("id", id);
        session.setAttribute("role", role);
        session.setAttribute("name", name);
        session.setMaxInactiveInterval(30 * 60);
        logger.trace("Authorization successfull");
    }

    public String logoutGet(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        logger.trace("Logout");
        return "redirect:/login";
    }

}
