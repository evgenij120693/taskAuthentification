package ru.svetozarov.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.svetozarov.common.exception.OrderDAOException;
import ru.svetozarov.models.pojo.Order;
import org.apache.log4j.Logger;
import ru.svetozarov.services.IOrderService;

import javax.servlet.ServletConfig;
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

    private IOrderService IOrderService;
    @Autowired
    @Qualifier("orderService")
    public void setOrderService(IOrderService IOrderService) {
        this.IOrderService = IOrderService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int id_client = (int) session.getAttribute("id");
        try {
            Order order = IOrderService.getOrderActualByClient(id_client);
            if(order != null){
                req.setAttribute("order", order);
                req.getRequestDispatcher("/client/actual_order.jsp").forward(req, resp);
            }else{
                req.getRequestDispatcher("/client/create_order.jsp").forward(req, resp);
            }

        }catch (OrderDAOException e){
            logger.error(e);
            resp.sendRedirect("taxi/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        int id_client = (int) session.getAttribute("id");
        Order order = new Order(
                0,
                id_client,
                new Timestamp(System.currentTimeMillis()).toString(),
                req.getParameter("punkt_a"),
                req.getParameter("punkt_b"),
                Integer.valueOf(req.getParameter("price")),
                0,
                null,
                null,
                1
        );
        try {
            if(IOrderService.addOrder(order)){
                logger.trace("Order registration successfull");
                resp.sendRedirect("/taxi/client/taxi");
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
