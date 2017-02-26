package controllers.admin;

import common.exception.AutoDAOException;
import models.pojo.Auto;
import org.apache.log4j.Logger;
import services.AutoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
public class ListAutoServlet extends HttpServlet{
    private static Logger logger = Logger.getLogger(ListDriverServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Auto> list = AutoService.getAllAuto();
            logger.trace("get all auto count="+list.size());
            req.setAttribute("list", list);
            req.getRequestDispatcher("/admin/list_auto.jsp").forward(req, resp);
        } catch (AutoDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
