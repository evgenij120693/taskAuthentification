package models.dao;

import common.exception.ConnectorException;
import common.exception.UserDAOException;
import models.connector.Connector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Шмыга on 25.02.2017.
 */
public class UserDAO {

    private static Logger logger = Logger.getLogger(UserDAO.class);
    private static final String QUERY_SELECT_ALL_BY_LOGIN = "select login, password from taxi.admin" +
            " where login=? " +
            " union select login, password from  taxi.client where login=? " +
            " union select login, password from  taxi.driver where login=? ";

    private static final String QUERY_SELECT_ALL_BY_LOGIN_AND_ID = "select login, password from taxi.admin" +
            " where login=? and id<>? " +
            " union select login, password from  taxi.client where login=? and id<>? " +
            " union select login, password from  taxi.driver where login=? and id<>?";


    public static boolean checkUserByLogin(String login) throws UserDAOException {
        try {
            try(Connection conn = Connector.getConnection();
                PreparedStatement prepS = conn.prepareStatement(QUERY_SELECT_ALL_BY_LOGIN)){
                prepS.setString(1, login);
                prepS.setString(2, login);
                prepS.setString(3, login);

                ResultSet resultSet = prepS.executeQuery();
                if (resultSet.next()) {
                    return false;
                } else {
                    logger.debug(login + " not found");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return  true;
    }

    public static boolean checkUserByLogin(String login, int id) throws UserDAOException {
        try {
            try(Connection conn = Connector.getConnection();
                PreparedStatement prepS = conn.prepareStatement(QUERY_SELECT_ALL_BY_LOGIN_AND_ID)){
                prepS.setString(1, login);
                prepS.setInt(2, id);
                prepS.setString(3, login);
                prepS.setInt(4, id);
                prepS.setString(5, login);
                prepS.setInt(6, id);

                ResultSet resultSet = prepS.executeQuery();
                if (resultSet.next()) {
                    logger.debug(login + " and "+id+"  found");
                    return false;
                } else {
                    logger.debug(login + " and "+id+" not found");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return  true;
    }
}
