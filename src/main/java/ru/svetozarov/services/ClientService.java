package ru.svetozarov.services;

import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.models.dao.ClientDAO;
import ru.svetozarov.models.pojo.Client;

import java.util.List;

/**
 * Created by Шмыга on 25.02.2017.
 */
public class ClientService {
    public static List<Client> getAllClients() throws ClientDAOException {
        return ClientDAO.getAllClient();
    }

    public static Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException {
        return ClientDAO.getClientByLoginAndPassword(login, password);
    }

    public static boolean addClient(Client client) throws ClientDAOException {
        return ClientDAO.addClient(client);
    }

    public  static Client getClientBiId( int id) throws ClientDAOException {
        return  ClientDAO.getClientById(id);
    }

    public static boolean updateClient(Client client) throws ClientDAOException {
        return ClientDAO.updateClient(client);
    }

    public  static boolean deleteClientById(int id) throws ClientDAOException {
        return ClientDAO.deleteClientById(id);
    }
}
