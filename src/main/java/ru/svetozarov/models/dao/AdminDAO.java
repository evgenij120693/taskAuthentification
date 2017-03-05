package ru.svetozarov.models.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.common.exception.ConnectorException;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.pojo.Admin;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Шмыга on 24.02.2017.
 */
@Repository(value = "adminDAO")
public class AdminDAO implements IAdminDAO {
    private static Logger logger = Logger.getLogger(AdminDAO.class);
    private  final String SQL_FIND_USER = "Select * from taxi.admin where login = ? and password = ?";
    private  final String SQL_ADMIN_BY_LOGIN = "Select * from taxi.admin where login = ?";
    private  final String SQL_ADMIN_BY_ID = "Select * from taxi.admin where id= ?";
    private  final String SQL_UPDATE_FLAG_SEND_MAIL = "update taxi.admin set send_mail_flag=?" +
            " where id=?";
    private  final String SQL_UPDATE_ADMIN = "update taxi.admin set password=?, name=?, " +
            " email=?, send_email_flag=? where id=?";

    private  final String SQL_INSERT_USER = "Insert into taxi.admin (login, password, name)" +
            " values (?, ?, ?)";

    @Override
    public  Admin getAdminByLoginAndPassword(String login, String password)
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
                admin.setEmail(resultSet.getString("email"));
                admin.setSendEmailFlag(resultSet.getInt("send_email_flag"));
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


    private  boolean checkUserByLogin(String login) throws UserDAOException {
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_ADMIN_BY_LOGIN)) {
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

    @Override
    public  Admin getAdminById(int id) throws UserDAOException {
        Admin admin = null;
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_ADMIN_BY_ID)) {
            prepS.setInt(1, id);
            ResultSet resultSet = prepS.executeQuery();
            if (resultSet.next()) {
                admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setLogin(resultSet.getString("login"));
                admin.setPassword(resultSet.getString("password"));
                admin.setName(resultSet.getString("name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setSendEmailFlag(resultSet.getInt("send_email_flag"));
            } else {
                logger.debug("Admin by id="+id+" not found");
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

    @Override
    public  boolean updateAdmin(Admin admin) throws UserDAOException {
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL_UPDATE_ADMIN);
            statement.setString(1, admin.getPassword());
            statement.setString(2, admin.getName());
            statement.setString(3, admin.getEmail());
            statement.setInt(4, admin.getSendEmailFlag());
            statement.setInt(5, admin.getId());
            int count = statement.executeUpdate();
            if(count > 0){
                logger.trace("Update flag  successful");
                return true;
            }else{
                logger.trace("Update flag FAILED");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return false;
    }

}
