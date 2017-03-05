package ru.svetozarov.models.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.svetozarov.common.exception.ConnectorException;
import ru.svetozarov.common.exception.StatusDAOException;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.pojo.Status;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
@Repository(value = "statusDAO")
public class StatusDAO implements IStatusDAO {
    private static Logger logger = Logger.getLogger(StatusDAO.class);
    private  final String QUERY_SELECT_ALL_STATUS_DRIVER = "select * from taxi.status_driver";
    private  final String QUERY_SELECT_STATUS_ORDER_BY_ID = "select * from taxi.status_order where id=?";

    @Override
    public  List<Status> getAllStatusDriver() throws StatusDAOException {
        List<Status> list = new ArrayList<>();

        try (Connection conn = Connector.getConnection();) {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(QUERY_SELECT_ALL_STATUS_DRIVER);
            while (result.next()) {
                Status status = new Status(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("description")
                );
                list.add(status);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new StatusDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new StatusDAOException();
        }
        return list;
    }

    @Override
    public  Status getStatusOrderById(int id) throws StatusDAOException {
        Status status = null;
        try (Connection conn = Connector.getConnection();) {
            PreparedStatement statement = conn.prepareStatement(QUERY_SELECT_STATUS_ORDER_BY_ID);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                 status = new Status(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("description")
                );

            }else {
                logger.trace("Select order not found");
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new StatusDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new StatusDAOException();
        }
        return status;
    }

}
