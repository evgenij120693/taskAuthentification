package ru.svetozarov.controllers.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.pojo.Admin;
import ru.svetozarov.services.IAdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Evgenij on 07.03.2017.
 */
@Controller
public class AdminAccountController {
    private static Logger logger = Logger.getLogger(AdminAccountController.class);

    private ru.svetozarov.services.IAdminService IAdminService;
    @Autowired
    @Qualifier("adminService")
    public void setAdminService(IAdminService IAdminService) {
        this.IAdminService = IAdminService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admminAccountGet(HttpServletRequest req ){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        try {
            Admin admin = IAdminService.getAdminById(id);
            if (admin != null) {
                modelAndView.addObject("admin", admin);
                modelAndView.setViewName("/admin/index");
            } else {
                modelAndView.setViewName("/error.jsp");
            }
        } catch (UserDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }

}
