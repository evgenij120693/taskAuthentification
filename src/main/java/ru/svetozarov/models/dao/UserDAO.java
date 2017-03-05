package ru.svetozarov.models.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.svetozarov.common.exception.ConnectorException;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Шмыга on 25.02.2017.
 */
@Repository(value = "userDAO")
public class UserDAO implements IUserDAO {

    private static Logger logger = Logger.getLogger(UserDAO.class);
    private  final String QUERY_SELECT_ALL_BY_LOGIN = "select login, password from taxi.admin" +
            " where login=? " +
            " union select login, password from  taxi.client where login=? " +
            " union select login, password from  taxi.driver where login=? ";

    private  final String QUERY_SELECT_ALL_BY_LOGIN_AND_ID = "select login, password from taxi.admin" +
            " where login=? and id<>? " +
            " union select login, password from  taxi.client where login=? and id<>? " +
            " union select login, password from  taxi.driver where login=? and id<>?";

    private  final String QUERY_SELECT_ALL_BY_LOGIN_AND_PASSWORD = "select id, login, password, name," +
            " 'admin' as role from taxi.admin  where login=? and password=? " +
            " union select id, login, password, name, 'client' as role  from  taxi.client where login=? and password=? " +
            "  union select id, login, password, first_name as 'name', 'driver' as role " +
            "from  taxi.driver where login=? and password=? ";

    @Override
    public  boolean checkUserByLogin(String login) throws UserDAOException {
        try {
            try (Connection conn = Connector.getConnection();
                 PreparedStatement prepS = conn.prepareStatement(QUERY_SELECT_ALL_BY_LOGIN)) {
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
        return true;
    }

    @Override
    public  boolean checkUserByLogin(String login, int id) throws UserDAOException {
        try {
            try (Connection conn = Connector.getConnection();
                 PreparedStatement prepS = conn.prepareStatement(QUERY_SELECT_ALL_BY_LOGIN_AND_ID)) {
                prepS.setString(1, login);
                prepS.setInt(2, id);
                prepS.setString(3, login);
                prepS.setInt(4, id);
                prepS.setString(5, login);
                prepS.setInt(6, id);

                ResultSet resultSet = prepS.executeQuery();
                if (resultSet.next()) {
                    logger.debug(login + " and " + id + "  found");
                    return false;
                } else {
                    logger.debug(login + " and " + id + " not found");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return true;
    }

    @Override
    public  User getUserByLoginAndPassword(String login, String password) throws UserDAOException {
        User user = null;
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement prepS = conn.prepareStatement(QUERY_SELECT_ALL_BY_LOGIN_AND_PASSWORD);
            prepS.setString(1, login);
            prepS.setString(2, password);
            prepS.setString(3, login);
            prepS.setString(4, password);
            prepS.setString(5, login);
            prepS.setString(6, password);
            ResultSet resultSet = prepS.executeQuery();
            if (resultSet.next()) {
                logger.debug(login + " and " + login + "  found");
                user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );

            } else {
                logger.debug(login + " and " + login + " not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return user;
    }
}
