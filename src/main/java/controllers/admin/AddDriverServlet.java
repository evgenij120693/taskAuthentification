package controllers.admin;

import common.exception.AutoDAOException;
import common.exception.DriverDAOException;
import common.exception.UserDAOException;
import models.pojo.Auto;
import models.pojo.Driver;
import org.apache.log4j.Logger;
import services.AutoService;
import services.DriverService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
public class AddDriverServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ListDriverServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Auto> autoList = AutoService.getAllAuto();
            req.setAttribute("listAuto", autoList);
            req.getRequestDispatcher("/admin/add_driver.jsp").forward(req, resp);
        } catch (AutoDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        int auto = (req.getParameter("auto") != null) ? Integer.valueOf(req.getParameter("auto")) : 0;
        int rating = (req.getParameter("rating") != null) ? Integer.valueOf(req.getParameter("rating")) : 0;
        Driver driver = new Driver(
                0,
                req.getParameter("lastName"),
                req.getParameter("firstName"),
                req.getParameter("phone"),
                req.getParameter("login"),
                req.getParameter("password"),
                rating,
                auto,
                1
        );
        try {
            if (UserService.checkUserByLogin(login)) {
                if (DriverService.addDriver(driver)) {
                    logger.trace("Driver added successful");
                    resp.sendRedirect("/taxi/admin/list_driver");
                } else {
                    logger.trace("Driver add failed");
                    resp.sendRedirect("/taxi/admini/list_driver");
                }
            } else {
                logger.trace("Login dublicated!!");
                resp.sendRedirect("/taxi/admini/list_driver");
            }
        } catch (UserDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        } catch (DriverDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }
    }
}
