package models.dao;

import common.exception.ConnectorException;
import common.exception.OrderDAOException;
import models.connector.Connector;
import models.pojo.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Шмыга on 01.03.2017.
 */
public class OrderDAO {

    public static Logger logger = Logger.getLogger(OrderDAO.class);
    public static final String SQL_ADD_ORDER = "insert into taxi.order set id_client=?, date_registration=?, " +
            " punkt_a=?, punkt_b=?, price=?, id_driver=?, start_date=?, end_date=?, id_status=? ";

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
}
