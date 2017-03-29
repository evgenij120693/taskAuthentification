package ru.svetozarov.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.svetozarov.common.exception.OrderDAOException;
import ru.svetozarov.models.pojo.Order;

import java.util.List;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IOrderDAO {
    @Autowired
    void setStatusDAO(IStatusDAO IStatusDAO);

    @Autowired
    void setDriverDAO(IDriverDAO IDriverDAO);

    @Autowired
    void setClientDAO(IClientDAO IClientDAO);

    boolean addOrder(Order order) throws OrderDAOException;

    List<Order> getListOrderByDriverAndStatus(int id_driver, int id_status) throws OrderDAOException;

    List<Order> getListOrderHistoryByClient(int id_client) throws OrderDAOException;

    Order getListOrderActualByClient(int id_client) throws OrderDAOException;

    boolean updateOrderClient(Order order) throws OrderDAOException;

    boolean cancelOrderClient(int id) throws OrderDAOException ;

    Order getOrderById(int id);
    List<Order> getActualOrderByDriver(int status)  throws OrderDAOException ;
}
