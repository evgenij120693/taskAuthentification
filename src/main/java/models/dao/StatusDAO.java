package models.dao;

import common.exception.ConnectorException;
import common.exception.StatusDAOException;
import models.connector.Connector;
import models.pojo.Status;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
public class StatusDAO {
    private static Logger logger = Logger.getLogger(StatusDAO.class);
    private static final String QUERY_SELECT_ALL_STATUS_DRIVER = "select * from taxi.status_driver";

    public static List<Status> getAllStatusDriver() throws StatusDAOException {
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
}
