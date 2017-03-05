package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.models.dao.IClientDAO;
import ru.svetozarov.models.pojo.Client;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Шмыга on 25.02.2017.
 */
@Service(value = "clientService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClientService implements IClientService {
    private IClientDAO IClientDAO;
    @Override
    @Autowired
    @Qualifier("clientDAO")
    public void setClientDAO(IClientDAO IClientDAO) {
        this.IClientDAO = IClientDAO;
    }

    @Override
    public  List<Client> getAllClients() throws ClientDAOException {
        return IClientDAO.getAllClient();
    }

    @Override
    public  Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException {
        return IClientDAO.getClientByLoginAndPassword(login, password);
    }

    @Override
    public  boolean addClient(Client client) throws ClientDAOException {
        return IClientDAO.addClient(client);
    }

    @Override
    public   Client getClientBiId(int id) throws ClientDAOException {
        return  IClientDAO.getClientById(id);
    }

    @Override
    public  boolean updateClient(Client client) throws ClientDAOException {
        return IClientDAO.updateClient(client);
    }

    @Override
    public   boolean deleteClientById(int id) throws ClientDAOException {
        return IClientDAO.deleteClientById(id);
    }
}
