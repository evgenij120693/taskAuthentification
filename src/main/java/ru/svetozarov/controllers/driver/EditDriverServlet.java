package ru.svetozarov.controllers.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.svetozarov.common.exception.DriverDAOException;
import ru.svetozarov.common.exception.StatusDAOException;
import ru.svetozarov.models.pojo.Driver;
import ru.svetozarov.models.pojo.Status;
import org.apache.log4j.Logger;
import ru.svetozarov.services.IDriverService;
import ru.svetozarov.services.IStatusService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Шмыга on 01.03.2017.
 */
public class EditDriverServlet extends HttpServlet {
    public static Logger logger = Logger.getLogger(EditDriverServlet.class);

    private IDriverService IDriverService;
    @Autowired
    @Qualifier("driverService")
    public void setDriverService(IDriverService IDriverService) {
        this.IDriverService = IDriverService;
    }

    private IStatusService IStatusService;
    @Autowired
    @Qualifier("statusService")
    public void setStatusService(IStatusService IStatusService) {
        this.IStatusService = IStatusService;
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
                Driver driver = IDriverService.getDriverByIdJoinAutoAndStatus(id);
                List<Status> statusList = IStatusService.getAllStatusDriver();
                if (driver != null) {
                    req.setAttribute("driver", driver);
                    req.setAttribute("statusList", statusList);
                    req.getRequestDispatcher("/driver/edit.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("/taxi/driver/");
                }
            } catch (DriverDAOException e) {
                logger.error(e);
                resp.sendRedirect("/taxi/error.jsp");
            } catch (StatusDAOException e) {
                logger.error(e);
                resp.sendRedirect("/taxi/error.jsp");
            }
        } else {
            resp.sendRedirect("/taxi/driver");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        try {
            Driver temp = IDriverService.getDriverById(id);
            Driver driver = new Driver(
                    id,
                    req.getParameter("lastName"),
                    req.getParameter("firstName"),
                    req.getParameter("phone"),
                    temp.getLogin(),
                    req.getParameter("password"),
                    temp.getRating(),
                    temp.getAuto(),
                    temp.getStatus()
                    );
            if(IDriverService.updateDriver(driver)){
                logger.trace("Update driver successful");
                resp.sendRedirect("/taxi/driver");
            }else{
                logger.trace("Update driver failed");
                resp.sendRedirect("/taxi/driver/edit");
            }
        }catch (DriverDAOException e){
            logger.error(e);
            resp.sendRedirect("taxi/error.jsp");
        }

    }
}
