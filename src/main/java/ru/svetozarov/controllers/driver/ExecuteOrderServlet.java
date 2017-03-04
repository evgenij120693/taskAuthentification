package ru.svetozarov.controllers.driver;

import ru.svetozarov.common.exception.OrderDAOException;
import ru.svetozarov.models.pojo.Order;
import org.apache.log4j.Logger;
import ru.svetozarov.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by Шмыга on 02.03.2017.
 */
public class ExecuteOrderServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ExecuteOrderServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int id = (!req.getParameter("id").equals(""))? Integer.valueOf(req.getParameter("id")):0;
        HttpSession session = req.getSession(false);
        int id_driver = (int) session.getAttribute("id");
        if(id != 0){
            Order order = new Order(
                    id,
                    0,
                    null,
                    null,
                    null,
                    0,
                    id_driver,
                    new Timestamp(System.currentTimeMillis()).toString(),
                    new Timestamp(System.currentTimeMillis()+60*30).toString(),
                    4
            );
            try {
                if (OrderService.updateOrderOfDriver(order)) {
                    logger.trace("Update successful");
                    resp.sendRedirect("/taxi/driver/list_new_order");
                }else{
                    logger.trace("Update failed");
                    resp.sendRedirect("/taxi/driver/list_new_order");
                }
            }catch (OrderDAOException e){
                logger.error(e);
                resp.sendRedirect("/taxi/error.jsp");
            }
        }else{
            resp.sendRedirect("/taxi/driver/new_list_order");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
