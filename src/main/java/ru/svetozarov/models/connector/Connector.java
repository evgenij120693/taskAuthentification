package ru.svetozarov.models.connector;


import ru.svetozarov.common.exception.ConnectorException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
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

    /**
     * Функция возвращает connection
     *
     * @return - возвращает ссылку на connection
     * @throws SQLException
     */
    public static Connection getConnection() throws ConnectorException {
        // if(connection == null) {
        try {
            Context initContext = null;
            initContext = new InitialContext();
            Context environmentContext = (Context) initContext.lookup("java:comp/env");
            String dataResourceName = "jdbc/dbconnect";
            DataSource ds = null;
            ds = (DataSource) environmentContext.lookup(dataResourceName);

            Connection conn = (Connection) ds.getConnection();
            return conn;
        }catch (SQLException e) {
            logger.error(e);
            throw new ConnectorException();
        }
        catch (NamingException e) {
            logger.error(e);
            throw new  ConnectorException();
        }
    }
}
