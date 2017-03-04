package ru.svetozarov.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.svetozarov.common.exception.AutoDAOException;
import org.apache.log4j.Logger;
import ru.svetozarov.services.AutoService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Шмыга on 26.02.2017.
 */
public class DeleteAutoServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(DeleteAutoServlet.class);

    private AutoService autoService;
    @Autowired
    public void setAutoService(AutoService autoService) {
        this.autoService = autoService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = (req.getParameter("id") != null)? Integer.valueOf(req.getParameter("id")):0;
        if(id != 0){
            try {
                if(autoService.deleteAuto(id)){
                    logger.trace("Delete auto by id="+id+" successful");
                    resp.sendRedirect("/taxi/admin/list_auto");
                }else{
                    logger.trace("Delete auto by id="+id+" failed");
                    resp.sendRedirect("/taxi/admin/list_auto");
                }
            } catch (AutoDAOException e) {
                logger.error(e);
                resp.sendRedirect("/taxi/error.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
