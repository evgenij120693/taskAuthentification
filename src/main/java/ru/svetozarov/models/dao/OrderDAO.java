package ru.svetozarov.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.svetozarov.common.exception.*;
import ru.svetozarov.common.util.Factory;
import ru.svetozarov.models.entity.DriverEntity;
import ru.svetozarov.models.entity.OrderEntity;
import ru.svetozarov.models.entity.StatusOrderEntity;
import ru.svetozarov.models.mapper_entity.OrderMapper;
import ru.svetozarov.models.pojo.Order;
import org.apache.log4j.Logger;
import ru.svetozarov.models.repository.OrderRepository;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шмыга on 01.03.2017.
 */
@Transactional
@Repository(value = "orderDAO")
public class OrderDAO implements IOrderDAO {


    private IStatusDAO IStatusDAO;
    private IDriverDAO IDriverDAO;
    private IClientDAO IClientDAO;
    @Override
    @Autowired
    @Qualifier("statusDAO")
    public void setStatusDAO(IStatusDAO IStatusDAO) {
        this.IStatusDAO = IStatusDAO;
    }
    @Override
    @Autowired
    @Qualifier("driverDAO")
    public void setDriverDAO(IDriverDAO IDriverDAO) {
        this.IDriverDAO = IDriverDAO;
    }
    @Override
    @Autowired
    @Qualifier("clientDAO")
    public void setClientDAO(IClientDAO IClientDAO) {
        this.IClientDAO = IClientDAO;
    }


    OrderRepository orderRepository;
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public static Logger logger = Logger.getLogger(OrderDAO.class);
    private static final EntityManagerFactory FACTORY =
            Factory.getFACTORY();

    @Override
    public  boolean addOrder(Order order) throws OrderDAOException {

        orderRepository.save(OrderMapper.converterToOrderEntity(order));
        return true;
    }


    @Override
    public  List<Order> getListOrderByDriverAndStatus(int id_driver, int id_status) throws OrderDAOException {
        List<Order> list = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.
                findByEntytiStatusIdInAndEntityDriverIdIn(new ArrayList<Integer>(){{add(id_status);}},
                        new ArrayList<Integer>(){{add(id_driver);}});
        for (OrderEntity order :
                orderEntities) {
            list.add(OrderMapper.converterToOrder(order));
        }
        return list;
    }

    public List<Order> getActualOrderByDriver(int status)   throws OrderDAOException {
        List<Order> list = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.
                findByEntytiStatusIdAndEntityDriverIdIsNull(status);
        for (OrderEntity order :
                orderEntities) {
            list.add(OrderMapper.converterToOrder(order));
        }
        return list;
    }

    @Override
    public  List<Order> getListOrderHistoryByClient(int id_client) throws OrderDAOException {
        List<Order> list = new ArrayList<>();

        List<OrderEntity> orderEntities = orderRepository.
                findByEntytiStatusIdNotInAndEntityClientId(new ArrayList<Integer>(){{add(1);}},id_client);
        for (OrderEntity order :
                orderEntities) {
            list.add(OrderMapper.converterToOrder(order));
        }
        return list;
    }

    @Override
    public  Order getListOrderActualByClient(int id_client) throws OrderDAOException {
        List<Order> list = new ArrayList<>();

        List<OrderEntity> orderEntities = orderRepository.
                findByEntytiStatusIdNotInAndEntityClientId(new ArrayList<Integer>(){{add(4);add(5);}},id_client);
        for (OrderEntity order :
                orderEntities) {
            list.add(OrderMapper.converterToOrder(order));
        }
        return (list.size()!=0)?list.get(0):null;
    }



    @Override
    public  boolean updateOrderClient(Order order) throws OrderDAOException {
        OrderEntity orderEntity = OrderMapper.converterToOrderEntity(order);
        System.out.println("update "+order.getEntityDriver().getId()+ " "+orderEntity.getEntityDriver().getId());
        orderRepository.save(orderEntity);
        return true;
    }

    @Override
    public boolean cancelOrderClient(int id) throws OrderDAOException {
        OrderEntity orderEntity = orderRepository.findById(id);
        StatusOrderEntity statusOrderEntity = new StatusOrderEntity();
        statusOrderEntity.setId(5);
        orderEntity.setEntytiStatus(statusOrderEntity);
        orderEntity.setEntityDriver(new DriverEntity(){{setId(1);}});
        orderRepository.save(orderEntity);
        return true;
    }

    public Order getOrderById(int id){
        Order order =OrderMapper.converterToOrder(orderRepository.findById(id));
        return order;
    }
}
