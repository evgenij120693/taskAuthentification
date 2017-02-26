package controllers.admin;

import common.exception.AutoDAOException;
import common.exception.DriverDAOException;
import common.exception.StatusDAOException;
import models.pojo.Auto;
import models.pojo.Driver;
import models.pojo.Status;
import org.apache.log4j.Logger;
import services.AutoService;
import services.DriverService;
import services.StatusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Шмыга on 25.02.2017.
 */
public class ListDriverServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ListDriverServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Driver> list = DriverService.getListDriver();
           /* List<Auto> listAuto = AutoService.getAllAuto();
            List<Status> statusList = StatusService.getAllStatusDriver();*/
            req.setAttribute("list", list);
            /*req.setAttribute("listAuto", listAuto);
            req.setAttribute("listStatus", statusList);*/
            req.getRequestDispatcher("/admin/list_driver.jsp").forward(req, resp);
        } catch (DriverDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        } /*catch (AutoDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        } catch (StatusDAOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
