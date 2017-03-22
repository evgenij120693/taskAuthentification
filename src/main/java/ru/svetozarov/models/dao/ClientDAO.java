package ru.svetozarov.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.common.exception.ConnectorException;
import ru.svetozarov.common.util.Factory;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.entity.ClientEntity;
import ru.svetozarov.models.mapper_entity.ClientMapper;
import ru.svetozarov.models.pojo.Client;
import org.apache.log4j.Logger;
import ru.svetozarov.models.repository.ClientRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шмыга on 25.02.2017.
 */
@Transactional
@Repository(value = "clientDAO")
public class ClientDAO implements IClientDAO {
    ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    private static Logger logger = Logger.getLogger(ClientDAO.class);
    private static final EntityManagerFactory FACTORY =
            Factory.getFACTORY();
    private final String QUERY_SELECT_ALL = "select * from taxi.client";
    private final String QUERY_SELECT_CLIENT_BY_LOGIN_AND_PASSWORD = "select * from taxi.client " +
            "where login=? and password=?";
    private final String QUERY_INSERT_CLIENT = "insert into taxi.client ( name, sex, phone, email," +
            "login, password ) values ( ?, ?, ?, ?, ?, ?)";
    private final String QUERY_SELECY_CLIENT_BY_ID = "select * from taxi.client where id=?";
    private final String QUERY_UPDATE_CLIENT = "update taxi.client set name=?, sex=?, phone=?, " +
            "email=?, login=?, password=? where id=?";
    private final String QUERY_DELETE_CLIENT = "delete from taxi.client where id=?";

    @Override
    public List<Client> getAllClient() throws ClientDAOException {
        List<Client> list = new ArrayList<>();
        Iterable<ClientEntity> clientEntities = clientRepository.findAll();
        for (ClientEntity client :
                clientEntities) {
            list.add(ClientMapper.converterToClient(client));
        }
        return list;
    }

    @Override
    public boolean addClient(Client client) throws ClientDAOException {
        ClientEntity clientEntity = ClientMapper.converterToClientEntity(client);
        clientRepository.save(clientEntity);
        return true;
    }

    @Override
    public Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException {
        List<Client> list = new ArrayList<>();
        Iterable<ClientEntity> clientEntities =
                clientRepository.findByLoginAndPassword(login, password);
        for (ClientEntity client :
                clientEntities) {
            list.add(ClientMapper.converterToClient(client));
        }
        return list.get(0);
    }

    @Override
    public Client getClientById(int id) throws ClientDAOException {
        Client client = null;
        ClientEntity listClientEntity = clientRepository.findOne(id);
        client = ClientMapper.converterToClient(listClientEntity);
        return client;
    }

    @Override
    public boolean updateClient(Client client) throws ClientDAOException {
        ClientEntity clientEntity = ClientMapper.converterToClientEntity(client);
        clientRepository.save(clientEntity);
        return true;
    }

    @Override
    public boolean deleteClientById(int id) throws ClientDAOException {
        clientRepository.delete(id);
        return  true;
    }

}
