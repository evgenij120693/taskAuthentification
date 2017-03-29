package ru.svetozarov.controllers.client;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.OrderDAOException;
import ru.svetozarov.models.pojo.Client;
import ru.svetozarov.models.pojo.Driver;
import ru.svetozarov.models.pojo.Order;
import ru.svetozarov.models.pojo.Status;
import ru.svetozarov.services.IOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * Created by Evgenij on 08.03.2017.
 */
@Controller
public class CreateOrderController {
    private static Logger logger = Logger.getLogger(CreateOrderController.class);

    private IOrderService iOrderService;

    @Autowired
    @Qualifier("orderService")
    public void setOrderService(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }

    @RequestMapping(value = "/client/taxi", method = RequestMethod.GET)
    public ModelAndView createOrderGet(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = req.getSession(false);
        int id_client = (int) session.getAttribute("id");
        try {
            Order order = iOrderService.getOrderActualByClient(id_client);
            if (order != null) {
                req.setAttribute("order", order);
                modelAndView.setViewName("/client/actual_order");
            } else {
                modelAndView.setViewName("/client/create_order");
            }

        } catch (OrderDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/client/taxi", method = RequestMethod.POST)
    public  ModelAndView createOrderPost(HttpServletRequest req,
                                               @RequestParam(name = "punkt_a") String punktA,
                                               @RequestParam(name = "punkt_b") String punktB,
                                               @RequestParam(name = "price") Integer price) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            HttpSession session = req.getSession(false);
            int id_client = (int) session.getAttribute("id");
            Client tempClient = new Client();
            tempClient.setId(id_client);
            Status tempStatus = new Status();
            tempStatus.setId(1);
            Driver tempDriver = new Driver();
            tempDriver.setId(1);
            Order order = new Order(
                    0,
                    tempClient,
                    new Timestamp(System.currentTimeMillis()).toString(),
                    punktA,
                    punktB,
                    price,
                    tempDriver,
                    null,
                    null,
                    tempStatus
            );

            if (iOrderService.addOrder(order)) {
                logger.trace("Order registration successfull");
                modelAndView.setViewName("redirect:/client/taxi");
            } else {
                logger.trace("Order registration failde");
                modelAndView.setViewName("redirect:/error.jsp");
            }
        } catch (OrderDAOException e) {
            logger.error(e);
            modelAndView.setViewName("redirect:/error.jsp");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/client/cancel_order", method = RequestMethod.GET)
    public ModelAndView closeOrderGet(@RequestParam (name = "id", defaultValue = "0")
                                                  Integer id){
        ModelAndView modelAndView = new ModelAndView();
        if(id != 0) {
            try {
                if(iOrderService.cancelOrderOfDriver(id)){
                    logger.trace("Cancel order succesfull");
                    modelAndView.setViewName("redirect:/client/taxi");
                }else{
                    logger.trace("cancel order failed");
                    modelAndView.setViewName("redirect:/client/taxi");
                }

            } catch (OrderDAOException e) {
                logger.error(e);
                modelAndView.setViewName("redirect:/error.jsp");
            }
        }else{
            modelAndView.setViewName("redirect:/client/taxi");
        }
        return  modelAndView;
    }

}
