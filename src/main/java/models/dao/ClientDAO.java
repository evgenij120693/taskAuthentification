package models.dao;

import common.exception.ClientDAOException;
import common.exception.ConnectorException;
import models.connector.Connector;
import models.pojo.Client;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шмыга on 25.02.2017.
 */
public class ClientDAO {
    private static Logger logger = Logger.getLogger(ClientDAO.class);
    private static final String QUERY_SELECT_ALL = "select * from taxi.client";
    private static final String QUERY_SELECT_CLIENT_BY_LOGIN_AND_PASSWORD = "select * from taxi.client " +
            "where login=? and password=?";
    private static final String QUERY_INSERT_CLIENT = "insert into taxi.client ( name, sex, phone, email," +
            "login, password ) values ( ?, ?, ?, ?, ?, ?)";
    private static final String QUERY_SELECY_CLIENT_BY_ID = "select * from taxi.client where id=?";
    private static final String QUERY_UPDATE_CLIENT = "update taxi.client set name=?, sex=?, phone=?, " +
            "email=?, login=?, password=? where id=?";
    private static final String QUERY_DELETE_CLIENT = "delete from taxi.client where id=?";

    public static List<Client> getAllClient() throws ClientDAOException {
        List<Client> list = new ArrayList<>();
        try (Connection conn = Connector.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL);
            while (resultSet.next()) {
                logger.trace("result select " + resultSet.getString("name"));
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("sex"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
                list.add(client);
            }
        } catch (ConnectorException e) {
            logger.error(e);
            throw new ClientDAOException();
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        return list;
    }

    public static boolean addClient(Client client) throws ClientDAOException {

        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = null;
            statement = conn.prepareStatement(QUERY_INSERT_CLIENT);
            statement.setString(1, client.getName());
            statement.setString(2, client.getSex());
            statement.setString(3, client.getPhone());
            statement.setString(4, client.getEmail());
            statement.setString(5, client.getLogin());
            statement.setString(6, client.getPassword());
            int count = statement.executeUpdate();
            if (count > 0) {
                logger.trace("Inser t client " + client.getName() + " succesfull");
                return true;
            } else {
                logger.trace("Inser t client " + client.getName() + " failed");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        return false;
    }

    public static Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException {
        Client client = null;

        try (Connection conn = Connector.getConnection();
             PreparedStatement prepSt = conn.prepareStatement(QUERY_SELECT_CLIENT_BY_LOGIN_AND_PASSWORD)) {
            prepSt.setString(1, login);
            prepSt.setString(2, password);
            ResultSet resultSet = prepSt.executeQuery();
            if (resultSet.next()) {
                logger.trace("client " + resultSet.getString(2));
                client = new Client(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)

                );
            } else {
                logger.debug(login + " " + password + " not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        return client;
    }

    public static Client getClientById(int id) throws ClientDAOException {
        Client client = null;

        try (Connection conn = Connector.getConnection();
             PreparedStatement prepSt = conn.prepareStatement(QUERY_SELECY_CLIENT_BY_ID)) {
            prepSt.setInt(1, id);
            ResultSet resultSet = prepSt.executeQuery();
            if (resultSet.next()) {
                logger.trace("client " + resultSet.getString(2));
                client = new Client(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        return client;
    }

    public static boolean updateClient(Client client) throws ClientDAOException {
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = null;
            statement = conn.prepareStatement(QUERY_UPDATE_CLIENT);
            statement.setString(1, client.getName());
            statement.setString(2, client.getSex());
            statement.setString(3, client.getPhone());
            statement.setString(4, client.getEmail());
            statement.setString(5, client.getLogin());
            statement.setString(6, client.getPassword());
            statement.setInt(7, client.getId());
            int count = statement.executeUpdate();
            if (count > 0) {
                logger.trace("Update client " + client.getName() + " succesfull");
                return true;
            } else {
                logger.trace("Update  client " + client.getName() + " failed");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        return false;
    }

    public static boolean deleteClientById(int id) throws ClientDAOException {

        try (Connection conn = Connector.getConnection()) {
            PreparedStatement prepS = conn.prepareStatement(QUERY_DELETE_CLIENT);
            prepS.setInt(1, id);
            int count  = prepS.executeUpdate();
            if (count > 0){
                logger.trace("Delete successfull");
                return true;
            }else{
                logger.trace("Delete failed");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        return false;
    }

}
