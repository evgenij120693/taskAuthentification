package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    boolean addOrder(Order order) throws OrderDAOException;

    /*public  List<Order> getListNewOrder() throws OrderDAOException {
            return orderDAO.getListNewOrder();
        }*/
    List<Order> getListOrderByDriverAndStatus(int id_driver, int id_status) throws OrderDAOException;

    boolean updateOrderOfDriver(Order order) throws OrderDAOException;

    Order getOrderActualByClient(int id_client) throws OrderDAOException;

    List<Order> getListOrderHistoryByClient(int id_client) throws OrderDAOException;

    boolean cancelOrderOfDriver(int id) throws OrderDAOException;
}
