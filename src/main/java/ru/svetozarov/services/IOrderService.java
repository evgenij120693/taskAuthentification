package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import ru.svetozarov.common.exception.OrderDAOException;
import ru.svetozarov.models.dao.IOrderDAO;
import ru.svetozarov.models.pojo.Order;

import java.util.List;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IOrderService {
    @Autowired
    @Qualifier("orderDAO")
    void setOrderDAO(IOrderDAO IOrderDAO);

    @Secured({"ROLE_USER"})
    boolean addOrder(Order order) throws OrderDAOException;

    /*public  List<Order> getListNewOrder() throws OrderDAOException {
            return orderDAO.getListNewOrder();
        }*/
    @Secured({"ROLE_DRIVER", "ROLE_USER"})
    List<Order> getListOrderByDriverAndStatus(int id_driver, int id_status) throws OrderDAOException;

    @Secured({"ROLE_DRIVER", "ROLE_USER"})
    boolean updateOrderOfDriver(Order order) throws OrderDAOException;

    @Secured({"ROLE_DRIVER", "ROLE_USER"})
    Order getOrderActualByClient(int id_client) throws OrderDAOException;

    @Secured("ROLE_USER")
    List<Order> getListOrderHistoryByClient(int id_client) throws OrderDAOException;

    @Secured("ROLE_USER")
    boolean cancelOrderOfDriver(int id) throws OrderDAOException;

    @Secured({"ROLE_DRIVER", "ROLE_USER"})
    Order getOrderById(int id);

    @Secured({"ROLE_DRIVER", "ROLE_USER"})
    List<Order> getActualOrderByDriver(int status)  throws OrderDAOException ;
}
