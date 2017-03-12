package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.OrderDAOException;
import ru.svetozarov.models.dao.IOrderDAO;
import ru.svetozarov.models.pojo.Order;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Шмыга on 01.03.2017.
 */
@Service(value = "orderService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderService implements IOrderService {

    private IOrderDAO IOrderDAO;
    @Override
    @Autowired
    @Qualifier("orderDAO")
    public void setOrderDAO(IOrderDAO IOrderDAO) {
        this.IOrderDAO = IOrderDAO;
    }

    @Override
    public boolean addOrder(Order order) throws OrderDAOException {
        return IOrderDAO.addOrder(order);
    }

    /*public  List<Order> getListNewOrder() throws OrderDAOException {
        return orderDAO.getListNewOrder();
    }*/
    @Override
    public  List<Order> getListOrderByDriverAndStatus(int id_driver, int id_status) throws OrderDAOException {
        return IOrderDAO.getListOrderByDriverAndStatus(id_driver, id_status);
    }

    @Override
    public  boolean updateOrderOfDriver(Order order) throws OrderDAOException {
        return IOrderDAO.updateOrderClient(order);
    }

    @Override
    public  Order getOrderActualByClient(int id_client) throws OrderDAOException {
        return IOrderDAO.getListOrderActualByClient(id_client);
    }

    @Override
    public  List<Order> getListOrderHistoryByClient(int id_client) throws OrderDAOException {
        return IOrderDAO.getListOrderHistoryByClient(id_client);
    }

    @Override
    public boolean cancelOrderOfDriver(int id) throws OrderDAOException {
        return IOrderDAO.cancelOrderClient(id);
    }
}
