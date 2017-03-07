package ru.svetozarov.controllers.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.pojo.Admin;
import ru.svetozarov.services.IAdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class EditAccountController {
    private static Logger logger = Logger.getLogger(EditAccountController.class);

    private ru.svetozarov.services.IAdminService IAdminService;
    @Autowired
    @Qualifier("adminService")
    public void setAdminService(IAdminService IAdminService) {
        this.IAdminService = IAdminService;
    }

    @RequestMapping(value = "/admin/edit_account", method = RequestMethod.GET)
    public ModelAndView editGet(HttpServletRequest req){
        ModelAndView modelAndView = new ModelAndView();
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        if (id != 0) {
            try {
                Admin admin = IAdminService.getAdminById(id);
                if (admin != null) {
                    modelAndView.addObject("admin", admin);
                    modelAndView.setViewName("/admin/edit_account");
                } else {
                    modelAndView.setViewName("redirect:/admin/");
                }
            } catch (UserDAOException e) {
                logger.error(e);
                modelAndView.setViewName("redirect:/error.jsp");
            }
        } else {
            modelAndView.setViewName("/taxi/admin");
        }
       return modelAndView;
    }

    @RequestMapping(value = "/admin/edit_account", method = RequestMethod.POST)
    public ModelAndView editPost(HttpServletRequest req,
                                 @RequestParam(name = "name") String name,
                                 @RequestParam(name = "email") String email,
                                 @RequestParam(name = "flag", defaultValue = "0") String flag){
        ModelAndView modelAndView = new ModelAndView();
        String role = "client";
        String greetings = "";
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        int flagInt = (flag!=null && flag.equals("on"))
                ?1:0;
        try {
            Admin temp = IAdminService.getAdminById(id);
            Admin admin = new Admin(
                    id,
                    temp.getLogin(),
                    temp.getPassword(),
                    name,
                    email,
                    flagInt
            );

            if (IAdminService.updateAdmin(admin)) {
                logger.trace("Admin " + admin.getName() + " updated");
                modelAndView.setViewName("redirect:/admin");
            } else {
                logger.trace("Error update client");
                modelAndView.setViewName("redirect:/admin");
            }
        } catch (UserDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }
}
