package ru.svetozarov.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.svetozarov.common.exception.OrderDAOException;
import ru.svetozarov.models.pojo.Order;
import org.apache.log4j.Logger;
import ru.svetozarov.services.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Evgenij on 03.03.2017.
 */
public class ListOrderServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ListOrderServlet.class);

    private OrderService orderService;
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        try{
            List<Order> list = orderService.getListOrderHistoryByClient(id);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/client/list_done_order.jsp").forward(req, resp);
        }catch (OrderDAOException e){
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
