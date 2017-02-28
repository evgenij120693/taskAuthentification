package controllers.client;

import common.exception.OrderDAOException;
import models.dao.OrderDAO;
import models.pojo.Order;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by Шмыга on 28.02.2017.
 */
public class CreateOrderServlet extends HttpServlet {
    public static Logger logger = Logger.getLogger(CreateOrderServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/client/create_order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        Order order = new Order(
                0,
                id,
                req.getParameter("punkt_a"),
                req.getParameter("punkt_b"),
                Integer.valueOf(req.getParameter("price")),
                new Timestamp(System.currentTimeMillis()).toString(),
                null,
                null,
                0,
                1
        );
        try {
            if(OrderDAO.addOrder(order)){
                logger.trace("Order registration successfull");
                resp.sendRedirect("taxi/client");
            }else {
                logger.trace("Order registration failde");
                resp.sendRedirect("/taxi/error.jsp");
            }
        } catch (OrderDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }
    }
}
