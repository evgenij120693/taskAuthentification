package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.models.dao.IClientDAO;
import ru.svetozarov.models.pojo.Client;

import java.util.List;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IClientService {
    @Autowired
    @Qualifier("clientDAO")
    void setClientDAO(IClientDAO IClientDAO);

    List<Client> getAllClients() throws ClientDAOException;

    Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException;

    boolean addClient(Client client) throws ClientDAOException;

    Client getClientBiId(int id) throws ClientDAOException;

    boolean updateClient(Client client) throws ClientDAOException;

    boolean deleteClientById(int id) throws ClientDAOException;
}
