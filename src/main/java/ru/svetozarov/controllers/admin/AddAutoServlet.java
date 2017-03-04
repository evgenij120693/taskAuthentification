package ru.svetozarov.controllers.admin;

import ru.svetozarov.common.exception.AutoDAOException;
import ru.svetozarov.models.pojo.Auto;
import org.apache.log4j.Logger;
import ru.svetozarov.services.AutoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Шмыга on 26.02.2017.
 */
public class AddAutoServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AddAutoServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/add_auto.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Auto auto = new Auto(
                0,
                req.getParameter("marka"),
                req.getParameter("model"),
                req.getParameter("regNumber"),
                req.getParameter("color")
        );
        try {
            if(AutoService.addAuto(auto)){
                logger.trace("adding successful ");
                resp.sendRedirect("/taxi/admin/list_auto");
            }else{
                logger.trace("adding failed ");
                resp.sendRedirect("/taxi/admin/list_auto");
            }
        } catch (AutoDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }
    }
}