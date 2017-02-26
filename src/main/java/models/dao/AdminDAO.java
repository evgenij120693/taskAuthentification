package models.dao;

import common.exception.UserDAOException;
import common.exception.ConnectorException;
import models.connector.Connector;
import models.pojo.Admin;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Шмыга on 24.02.2017.
 */
public class AdminDAO {
    private static Logger logger = Logger.getLogger(AdminDAO.class);
    private static final String SQL_FIND_USER = "Select * from taxi.admin where login = ? and password = ?";
    private static final String SQL_USER_BY_LOGIN = "Select * from taxi.admin where login = ?";
    private static final String SQL_INSERT_USER = "Insert into taxi.admin (login, password, name)" +
            " values (?, ?, ?)";

    public static Admin getAdminByLoginAndPassword(String login, String password)
            throws UserDAOException {
        Admin admin = null;
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_FIND_USER)) {
            prepS.setString(1, login);
            prepS.setString(2, password);
            ResultSet resultSet = prepS.executeQuery();
            if (resultSet.next()) {
                admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setLogin(resultSet.getString("login"));
                admin.setPassword(resultSet.getString("password"));
                admin.setName(resultSet.getString("name"));
            } else {
                logger.debug(login + " " + password + " not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UserDAOException();
        } catch (ConnectorException e) {
            logger.error(e.getMessage());
            throw new UserDAOException();
        }
        return admin;
    }

    /*public static int addUser(String login, String password, String name) throws UserDAOException {
        if (checkUserByLogin(login)) {
            try (Connection conn = Connector.getConnection();
                 PreparedStatement prepS = conn.prepareStatement(SQL_INSERT_USER)) {
                prepS.setString(1, login);
                prepS.setString(2, password);
                prepS.setString(3, role);
                int count = prepS.executeUpdate();
                if (count > 0) {
                    logger.trace("Insert " + name + " successfull");
                    //return res.getInt(1);
                    ResultSet res = prepS.getGeneratedKeys();
                    if(res.next()){
                        return  res.getInt(1);
                    }
                } else {
                    logger.trace("Insert " + name + " unsuccessfull");
                    return 0;
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new UserDAOException();
            } catch (ConnectorException e) {
                logger.error(e.getMessage());
                throw new UserDAOException();
            }
        }
        return -1;
    }*/

    private static boolean checkUserByLogin(String login) throws UserDAOException {
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_USER_BY_LOGIN)) {
            prepS.setString(1, login);
            ResultSet resultSet = prepS.executeQuery();
            if (resultSet.next()) {
                return false;
            } else {
                logger.debug(login + " not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UserDAOException();
        } catch (ConnectorException e) {
            logger.error(e.getMessage());
            throw new UserDAOException();
        }
        return true;
    }

}
