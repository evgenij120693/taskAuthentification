package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_ADMIN")
    List<Client> getAllClients() throws ClientDAOException;

    @Secured("ROLE_ANONYMOUS")
    Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException;

    @Secured("ROLE_ADMIN")
    boolean addClient(Client client) throws ClientDAOException;

    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_DRIVER"})
    Client getClientBiId(int id) throws ClientDAOException;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    boolean updateClient(Client client) throws ClientDAOException;

    @Secured("ROLE_ADMIN")
    boolean deleteClientById(int id) throws ClientDAOException;
}
