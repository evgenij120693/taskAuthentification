package ru.svetozarov.models.dao;

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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шмыга on 25.02.2017.
 */
@Repository(value = "clientDAO")
public class ClientDAO implements IClientDAO {
    private static Logger logger = Logger.getLogger(ClientDAO.class);
    private static final EntityManagerFactory FACTORY =
            Factory.getFACTORY();
    private  final String QUERY_SELECT_ALL = "select * from taxi.client";
    private  final String QUERY_SELECT_CLIENT_BY_LOGIN_AND_PASSWORD = "select * from taxi.client " +
            "where login=? and password=?";
    private  final String QUERY_INSERT_CLIENT = "insert into taxi.client ( name, sex, phone, email," +
            "login, password ) values ( ?, ?, ?, ?, ?, ?)";
    private  final String QUERY_SELECY_CLIENT_BY_ID = "select * from taxi.client where id=?";
    private  final String QUERY_UPDATE_CLIENT = "update taxi.client set name=?, sex=?, phone=?, " +
            "email=?, login=?, password=? where id=?";
    private  final String QUERY_DELETE_CLIENT = "delete from taxi.client where id=?";

    @Override
    public  List<Client> getAllClient() throws ClientDAOException {
        List<Client> list = new ArrayList<>();
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ClientEntity> criteriaQuery = criteriaBuilder.createQuery(ClientEntity.class);
        Root<ClientEntity> root = criteriaQuery.from(ClientEntity.class);
        criteriaQuery.select(root);
        /*criteriaQuery.where(
                criteriaBuilder.and(
                )
        );*/
        List<ClientEntity> listDriverEntity = em.createQuery(criteriaQuery).getResultList();

        for (ClientEntity client :
                listDriverEntity) {

            list.add(ClientMapper.converterToClient(client));
        }
        return list;
    }

    @Override
    public  boolean addClient(Client client) throws ClientDAOException {

        ClientEntity clientEntity = ClientMapper.converterToClientEntity(client);
        EntityManager em = FACTORY.createEntityManager();
        em.getTransaction().begin();
        em.merge(clientEntity);
        em.getTransaction().commit();
        logger.trace("Result insert client"+em.contains(clientEntity));
        em.close();
        return true;
    }

    @Override
    public  Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException {
        Client client = null;
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ClientEntity> criteriaQuery = criteriaBuilder.createQuery(ClientEntity.class);
        Root<ClientEntity> root = criteriaQuery.from(ClientEntity.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("login"), login),
                        criteriaBuilder.equal(root.get("password"), password)
                )
        );
        List<ClientEntity> listClientEntity = em.createQuery(criteriaQuery).getResultList();
        client = ClientMapper.converterToClient(listClientEntity.get(0));
        return client;
        /*try (Connection conn = Connector.getConnection();
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
        return client;*/
    }

    @Override
    public  Client getClientById(int id) throws ClientDAOException {
        Client client = null;
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ClientEntity> criteriaQuery = criteriaBuilder.createQuery(ClientEntity.class);
        Root<ClientEntity> root = criteriaQuery.from(ClientEntity.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), id)
                )
        );
        List<ClientEntity> listClientEntity = em.createQuery(criteriaQuery).getResultList();
        client = ClientMapper.converterToClient(listClientEntity.get(0));
        return client;
    }

    @Override
    public  boolean updateClient(Client client) throws ClientDAOException {
        ClientEntity clientEntity = ClientMapper.converterToClientEntity(client);
        EntityManager em = FACTORY.createEntityManager();
        em.getTransaction().begin();
        em.merge(clientEntity);
        em.getTransaction().commit();
        logger.trace("Result update client"+em.contains(clientEntity));
        em.close();
        return true;
    }

    @Override
    public  boolean deleteClientById(int id) throws ClientDAOException {

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
