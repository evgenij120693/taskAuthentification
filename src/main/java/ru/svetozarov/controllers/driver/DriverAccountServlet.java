package ru.svetozarov.controllers.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.svetozarov.common.exception.DriverDAOException;
import ru.svetozarov.models.pojo.Driver;
import org.apache.log4j.Logger;
import ru.svetozarov.services.DriverService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Шмыга on 25.02.2017.
 */
public class DriverAccountServlet extends HttpServlet {
    public static Logger logger = Logger.getLogger(DriverAccountServlet.class);

    private DriverService driverService;
    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
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
                Driver driver = driverService.getDriverByIdJoinAutoAndStatus(id);
                //List<Status> statusList =  StatusService.getAllStatusDriver();
                if (driver != null) {
                    req.setAttribute("driver", driver);
                    req.getRequestDispatcher("/driver/index.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("/taxi/driver/");
                }
            } catch (DriverDAOException e) {
                logger.error(e);
                resp.sendRedirect("/taxi/error.jsp");
            }
        } else {
            resp.sendRedirect("/taxi/driver");
        }
        req.getRequestDispatcher("/driver/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
