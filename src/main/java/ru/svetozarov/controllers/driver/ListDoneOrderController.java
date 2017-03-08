package ru.svetozarov.controllers.driver;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.OrderDAOException;
import ru.svetozarov.models.pojo.Order;
import ru.svetozarov.services.IOrderService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Evgenij on 08.03.2017.
 */
@Controller
public class ListDoneOrderController {
    private static Logger logger = Logger.getLogger(ListDoneOrderController.class);

    private ru.svetozarov.services.IOrderService IOrderService;
    @Autowired
    @Qualifier("orderService")
    public void setOrderService(IOrderService IOrderService) {
        this.IOrderService = IOrderService;
    }

    @RequestMapping(value = "/driver/list_done_order")
    public ModelAndView listDoneOrderGet(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        int id = (int) session.getAttribute("id");
        try{
            List<Order> list = IOrderService.getListOrderByDriverAndStatus(id, 4);
            modelAndView.addObject("list", list);
            modelAndView.setViewName("/driver/list_done_order");
        }catch (OrderDAOException e){
            logger.error(e);
            modelAndView.setViewName("redirect:/eror.jsp");
        }
        return modelAndView;
    }
}
