package ru.svetozarov.models.dao;

import ru.svetozarov.common.exception.*;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.pojo.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шмыга on 01.03.2017.
 */
public class OrderDAO {

    public static Logger logger = Logger.getLogger(OrderDAO.class);
    public static final String SQL_ADD_ORDER = "insert into taxi.order set id_client=?, date_registration=?, " +
            " punkt_a=?, punkt_b=?, price=?, id_driver=?, start_date=?, end_date=?, id_status=? ";

    public static final String SQL_SELECT_ALL_ORDER_BY_DRIVER_AND_STATUS = "Select * from taxi.order where id_driver=? and" +
            " id_status=?";

    public static final String SQL_UPDATE_ORDER_OF_DRIVER = "update taxi.order set id_driver=?, start_date=?," +
            " end_date=?, id_status=? where id=? ";

    public static final String SQL_SELECT_ACTUAL_ORDER_BY_CLIENT_AND_STATUS = "select * from taxi.order where id_client=? " +
            "and id_status<>4";
    public static final String SQL_SELECT_HISTORY_ORDER_BY_CLIENT = "select * from taxi.order where id_client=? " +
            "and id_status<>1";

    public static boolean addOrder(Order order) throws OrderDAOException {
        try (Connection conn = Connector.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL_ADD_ORDER)) {
            statement.setInt(1, order.getIdClient());
            statement.setString(2, order.getDateRegistration());
            statement.setString(3, order.getPunktA());
            statement.setString(4, order.getPunktB());
            statement.setInt(5, order.getPrice());
            statement.setInt(6, order.getIdDriver());
            statement.setString(7, order.getDateStart());
            statement.setString(8, order.getDateEnd());
            statement.setInt(9, order.getIdStatus());
            int count = statement.executeUpdate();
            if (count > 0) {
                logger.trace("Inser order  succesfull");
                return true;
            } else {
                logger.trace("Inser order failed");
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new OrderDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new OrderDAOException();
        }
        return false;
    }


    public static List<Order> getListOrderByDriverAndStatus(int id_driver, int id_status) throws OrderDAOException {
        List<Order> list = new ArrayList<>();
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_ALL_ORDER_BY_DRIVER_AND_STATUS);
            statement.setInt(1, id_driver);
            statement.setInt(2, id_status);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Order order = null;
                try {
                    order = new Order(
                            result.getInt(1),
                            ClientDAO.getClientById(result.getInt(2)),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            result.getInt(6),
                            DriverDAO.getDriverById(result.getInt(7)),
                            result.getString(8),
                            result.getString(9),
                            StatusDAO.getStatusOrderById(result.getInt(10))
                    );
                } catch (ClientDAOException e) {
                    logger.error(e);
                    throw new OrderDAOException();
                } catch (DriverDAOException e) {
                    logger.error(e);
                    throw new OrderDAOException();
                } catch (StatusDAOException e) {
                    logger.error(e);
                    throw new OrderDAOException();
                }
                list.add(order);
            }

        } catch (ConnectorException e) {
            logger.error(e);
            throw new OrderDAOException();
        } catch (SQLException e) {
            logger.error(e);
            throw new OrderDAOException();
        }
        return list;
    }

    public static List<Order> getListOrderHistoryByClient(int id_client) throws OrderDAOException {
        List<Order> list = new ArrayList<>();
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_HISTORY_ORDER_BY_CLIENT);
            statement.setInt(1, id_client);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Order order = null;
                try {
                    order = new Order(
                            result.getInt(1),
                            ClientDAO.getClientById(result.getInt(2)),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            result.getInt(6),
                            DriverDAO.getDriverById(result.getInt(7)),
                            result.getString(8),
                            result.getString(9),
                            StatusDAO.getStatusOrderById(result.getInt(10))
                    );
                } catch (ClientDAOException e) {
                    logger.error(e);
                    throw new OrderDAOException();
                } catch (DriverDAOException e) {
                    logger.error(e);
                    throw new OrderDAOException();
                } catch (StatusDAOException e) {
                    logger.error(e);
                    throw new OrderDAOException();
                }
                list.add(order);
            }

        } catch (ConnectorException e) {
            logger.error(e);
            throw new OrderDAOException();
        } catch (SQLException e) {
            logger.error(e);
            throw new OrderDAOException();
        }
        return list;
    }

    public static Order getListOrderActualByClient(int id_client) throws OrderDAOException {
       Order order = null;
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_ACTUAL_ORDER_BY_CLIENT_AND_STATUS);
            statement.setInt(1, id_client);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                try {
                    logger.trace("select order by client successfull");
                    order = new Order(
                            result.getInt(1),
                            ClientDAO.getClientById(result.getInt(2)),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            result.getInt(6),
                            DriverDAO.getDriverById(result.getInt(7)),
                            result.getString(8),
                            result.getString(9),
                            StatusDAO.getStatusOrderById(result.getInt(10))
                    );
                } catch (ClientDAOException e) {
                    logger.error(e);
                    throw new OrderDAOException();
                } catch (DriverDAOException e) {
                    logger.error(e);
                    throw new OrderDAOException();
                } catch (StatusDAOException e) {
                    logger.error(e);
                    throw new OrderDAOException();
                }
            }

        } catch (ConnectorException e) {
            logger.error(e);
            throw new OrderDAOException();
        } catch (SQLException e) {
            logger.error(e);
            throw new OrderDAOException();
        }
        return order;
    }



    public static boolean updateOrderClient(Order order) throws OrderDAOException {
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL_UPDATE_ORDER_OF_DRIVER);
            statement.setInt(1, order.getIdDriver());
            statement.setString(2, order.getDateStart());
            statement.setString(3, order.getDateEnd());
            statement.setInt(4, order.getIdStatus());
            statement.setInt(5,order.getId());
            int count = statement.executeUpdate();
            if(count > 0){
                logger.trace("Update order of driver successful");
                return true;
            }else{
                logger.trace("Update order of driver failed");
            }
        } catch (ConnectorException e) {
            logger.error(e);
            throw new OrderDAOException();
        } catch (SQLException e) {
            logger.error(e);
            throw new OrderDAOException();
        }
        return false;
    }
}
