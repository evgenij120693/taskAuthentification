package models.connector;

import com.mysql.jdbc.Connection;
import common.exception.ConnectorException;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Шмыга on 23.02.2017.
 */
public class Connector {
    private static Logger logger = Logger.getLogger(Connector.class);

    private static Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/taxi?useSSL=false&useUnicode=true&characterEncoding=utf-8";
    private final String LOGIN = "root";
    private final String PASSWORD = "1234";

    private Connector() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = (Connection) DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    /**
     * Функция возвращает connection, создает только один connection
     *
     * @return - возвращает ссылку на connection
     * @throws SQLException
     */
    public static Connection getConnection() throws ConnectorException {
        // if(connection == null) {

        try {
            new Connector();
        } catch (SQLException e) {
            logger.error(e);
            throw new ConnectorException();
        } catch (ClassNotFoundException e) {
            logger.error(e);
            throw new ConnectorException();
        }
        //}
        return connection;
    }
}
