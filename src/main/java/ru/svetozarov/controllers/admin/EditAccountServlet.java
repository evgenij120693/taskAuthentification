package ru.svetozarov.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.pojo.Admin;
import org.apache.log4j.Logger;
import ru.svetozarov.services.IAdminService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Шмыга on 28.02.2017.
 */
public class EditAccountServlet extends HttpServlet {
    public static Logger logger = Logger.getLogger(EditAccountServlet.class);

    private IAdminService IAdminService;
    @Autowired
    @Qualifier("adminService")
    public void setAdminService(IAdminService IAdminService) {
        this.IAdminService = IAdminService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        if (id != 0) {
            try {
                Admin admin = IAdminService.getAdminById(id);
                if (admin != null) {
                    req.setAttribute("admin", admin);
                    req.getRequestDispatcher("/admin/edit_account.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("/taxi/admin/");
                }
            } catch (UserDAOException e) {
                logger.error(e);
                resp.sendRedirect("/taxi/error.jsp");
            }
        } else {
            resp.sendRedirect("/taxi/admin");
        }
        req.getRequestDispatcher("/admin/edit_account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String role = "client";
        String greetings = "";
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        int flag = (req.getParameter("flag")!=null && req.getParameter("flag").equals("on"))
                ?1:0;
        try {
            Admin temp = IAdminService.getAdminById(id);
            Admin admin = new Admin(
                    id,
                    temp.getLogin(),
                    req.getParameter("password"),
                    req.getParameter("name"),
                    req.getParameter("email"),
                    flag
            );

            if (IAdminService.updateAdmin(admin)) {
                logger.trace("Admin " + admin.getName() + " updated");
                resp.sendRedirect("/taxi/admin");
            } else {
                logger.trace("Error update client");
                resp.sendRedirect("/taxi/admin/");
            }
        } catch (UserDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }
    }
}
