package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.models.dao.ClientDAO;
import ru.svetozarov.models.pojo.Client;

import java.util.List;

/**
 * Created by Шмыга on 25.02.2017.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClientService {
    private ClientDAO clientDAO;
    @Autowired
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public  List<Client> getAllClients() throws ClientDAOException {
        return clientDAO.getAllClient();
    }

    public  Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException {
        return clientDAO.getClientByLoginAndPassword(login, password);
    }

    public  boolean addClient(Client client) throws ClientDAOException {
        return clientDAO.addClient(client);
    }

    public   Client getClientBiId( int id) throws ClientDAOException {
        return  clientDAO.getClientById(id);
    }

    public  boolean updateClient(Client client) throws ClientDAOException {
        return clientDAO.updateClient(client);
    }

    public   boolean deleteClientById(int id) throws ClientDAOException {
        return clientDAO.deleteClientById(id);
    }
}
