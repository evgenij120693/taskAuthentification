package ru.svetozarov.controllers.client;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
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
@SessionAttributes({"id", "role"})
public class ListOrderController {
    private static Logger logger = Logger.getLogger(ListOrderController.class);

    private ru.svetozarov.services.IOrderService IOrderService;
    @Autowired
    @Qualifier("orderService")
    public void setOrderService(IOrderService IOrderService) {
        this.IOrderService = IOrderService;
    }

    @RequestMapping(value = "/client/history_order", method = RequestMethod.GET)
    public ModelAndView listOrderGet( HttpSession session ){
        ModelAndView modelAndView = new ModelAndView();
        int id = (int) session.getAttribute("id");
        try{
            List<Order> list = IOrderService.getListOrderHistoryByClient(id);
            modelAndView.addObject("list", list);
            modelAndView.setViewName("/client/list_done_order");
        }catch (OrderDAOException e){
            logger.error(e);
            modelAndView.setViewName("redirect:/error");
        }
        return modelAndView;
    }

}
