package ru.svetozarov.controllers.driver;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exception.OrderDAOException;
import ru.svetozarov.models.pojo.Order;
import ru.svetozarov.services.IOrderService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * Created by Evgenij on 08.03.2017.
 */
@Controller
public class ExecuteOrderController {

    private static Logger logger = Logger.getLogger(ExecuteOrderController.class);

    private ru.svetozarov.services.IOrderService IOrderService;
    @Autowired
    @Qualifier("orderService")
    public void setOrderService(IOrderService IOrderService) {
        this.IOrderService = IOrderService;
    }

    @RequestMapping(value = "/driver/order_execute")
    public ModelAndView executeOrderGet(HttpSession session,
                                        @RequestParam(name = "id", defaultValue = "0") Integer id){
        ModelAndView modelAndView = new ModelAndView();
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
                if (IOrderService.updateOrderOfDriver(order)) {
                    logger.trace("Update successful");
                    modelAndView.setViewName("redirect:/driver/list_new_order");
                }else{
                    logger.trace("Update failed");
                    modelAndView.setViewName("redirect:/driver/list_new_order");
                }
            }catch (OrderDAOException e){
                logger.error(e);
                modelAndView.setViewName("redirect:/error.jsp");
            }
        }else{
            modelAndView.setViewName("redirect:/driver/list_new_order");
        }
        return  modelAndView;
    }
}
