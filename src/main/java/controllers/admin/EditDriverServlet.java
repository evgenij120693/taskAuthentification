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
public class EditDriverServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EditDriverServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace(req.getParameter("id"));
        int id = (!req.getParameter("id").equals("") ) ? Integer.valueOf(req.getParameter("id")) : 0;
        try {
            Driver driver = DriverService.getDriverById(id);
            List<Auto> autoList = AutoService.getAllAuto();
            if (driver != null) {
                logger.trace("Get Driver by id=" + id);
                req.setAttribute("driver", driver);
                req.setAttribute("listAuto", autoList);
                req.getRequestDispatcher("/admin/edit_driver.jsp").forward(req, resp);
            }
        } catch (DriverDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        } catch (AutoDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        logger.trace(req.getParameter("id"));
        int id = (!req.getParameter("id").equals("") ) ? Integer.valueOf(req.getParameter("id")) : 0;
        int rating = (!req.getParameter("rating").equals("") ) ? Integer.valueOf(req.getParameter("rating")) : 0;
        int auto = (!req.getParameter("auto").equals("") ) ? Integer.valueOf(req.getParameter("auto")) : 0;
        int status = (!req.getParameter("status").equals("") ) ? Integer.valueOf(req.getParameter("status")) : 0;
        try {
            Driver driver = new Driver(
                    id,
                    req.getParameter("lastName"),
                    req.getParameter("firstName"),
                    req.getParameter("phone"),
                    req.getParameter("login"),
                    req.getParameter("password"),
                    rating,
                    auto,
                    status
            );
                if(UserService.checkUserByLoginAndId(req.getParameter("login"), id)) {
                    if(DriverService.updateDriver(driver)) {
                        logger.trace("Edit Driver by id=" + id);
                        resp.sendRedirect("/taxi/admin/list_driver");
                    }else{
                        logger.trace("Edit Driver by id=" + id + " failed");
                        resp.sendRedirect("/taxi/admin/edit_driver?id="+id);
                    }
                }else{
                    logger.trace("Edit Driver by id=" + id + " failed. Dublicate login!");
                    resp.sendRedirect("/taxi/admin/edit_driver?id="+id);
                }

        }catch (UserDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        } catch (DriverDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }
    }
}
