package ru.svetozarov.models.dao;

import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.models.pojo.Client;

import java.util.List;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IClientDAO {
    List<Client> getAllClient() throws ClientDAOException;

    boolean addClient(Client client) throws ClientDAOException;

    Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException;

    Client getClientById(int id) throws ClientDAOException;

    boolean updateClient(Client client) throws ClientDAOException;

    boolean deleteClientById(int id) throws ClientDAOException;
}
