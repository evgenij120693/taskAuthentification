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
import java.util.List;

/**
 * Created by Шмыга on 02.03.2017.
 */
public class ListDoneOrderServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ListDoneOrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        try{
            List<Order> list = OrderService.getListOrderByDriverAndStatus(id, 4);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/driver/list_done_order.jsp").forward(req, resp);
        }catch (OrderDAOException e){
            logger.error(e);
            resp.sendRedirect("/taxi/eror.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
