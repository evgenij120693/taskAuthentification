package ru.svetozarov.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String indexGet(HttpSession session){
        if(session.getAttribute("id") != null){
            return "redirect:/"+session.getAttribute("role");
        }
        return "/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(HttpSession session,Authentication authentication, SecurityContextHolder securityContextHolder) {
        if (authentication != null) {
            User user = null;
            try {
                user = IUserService.getUserByLogin
                        (SecurityContextHolder.getContext().getAuthentication().getName().toString());
                System.out.println("LOGIN");
                String url = null;
                switch (user.getRole()){
                    case "ROLE_ADMIN":
                        url = "admin";
                        break;
                    case "ROLE_USER":
                        url = "client";
                        break;
                    case "ROLE_DRIVER":
                        url = "driver";
                        break;
                }
                setSession(user.getId(), url, user.getName(), session);
                return "redirect:/"+url;
            } catch (UserDAOException e) {
                logger.error(e);
                return "redirect:/error.jsp";
            }
        } else {
            if(session.getAttribute("id") != null){
                return "redirect:/"+session.getAttribute("role");
            }
            return "/login";
        }
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
                //setSession(user.getId(), user.getRole(), user.getName(), request);
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

    private static void setSession(int id, String role, String name, HttpSession session) {
        session.setAttribute("id", id);
        session.setAttribute("role", role);
        session.setAttribute("name", name);
        session.setMaxInactiveInterval(30 * 60);
        logger.trace("Authorization successfull");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutGet(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        logger.trace("Logout");
        return "redirect:/login";
    }

}
