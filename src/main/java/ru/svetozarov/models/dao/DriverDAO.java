package ru.svetozarov.models.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.svetozarov.common.exception.ConnectorException;
import ru.svetozarov.common.exception.DriverDAOException;
import ru.svetozarov.common.util.Factory;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.entity.DriverEntity;
import ru.svetozarov.models.mapper_entity.DriverMapper;
import ru.svetozarov.models.pojo.Auto;
import ru.svetozarov.models.pojo.Driver;
import ru.svetozarov.models.pojo.Status;
import org.apache.log4j.Logger;
import ru.svetozarov.models.pojo.User;
import ru.svetozarov.models.repository.DriverRepository;

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
@Repository(value = "driverDAO")

public class DriverDAO implements IDriverDAO {

    private static Logger logger = Logger.getLogger(DriverDAO.class);

    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("ENTITY");


    DriverRepository driverRepository;
    @Autowired
    public void setDriverRepository(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    private final String QUERY_SELECT_DRIVER_BY_LOGIN_AND_PASSWORD = "select * from taxi.driver as dr " +
            "JOIN taxi.auto as au ON dr.id_auto = au.id JOIN taxi.status_driver as st ON dr.id_status = st.id" +
            " where dr.login=? and  dr.password=?";
    // private  final String QUERY_SELECT_ALL_DRIVER = "select * from taxi.driver";
    private final String QUERY_SELECT_ALL_DRIVER = "select * from taxi.driver as dr " +
            "JOIN taxi.auto as au ON dr.id_auto = au.id JOIN taxi.status_driver as st ON dr.id_status = st.id";
    private final String QUERY_ADD_DRIVER = "insert into taxi.driver (first_name, last_name, " +
            "phone_number, login, password, rating, id_auto, id_status) values (?,?,?,?,?,?,?,?)";
    private final String QUERY_UPDATE_DRIVER = "update taxi.driver set first_name=?," +
            " last_name=?, phone_number=?, login=?, password=?, rating=?, id_auto=?, id_status=?" +
            " where id=?";
    private final String QUERY_SELECT_DRIVER_BY_ID = "select *from taxi.driver where id=?";
    private final String QUERY_SELECT_DRIVER_BY_ID_JOIN_AUTO_AND_STATUS = "select * from taxi.driver as dr " +
            "JOIN taxi.auto as au ON dr.id_auto = au.id JOIN taxi.status_driver as st ON dr.id_status = st.id" +
            " where dr.id=?";
    private final String QUERY_DELETE_DRIVER_BY_ID = "delete from taxi.driver where id=?";

    @Override
    public Driver getDriverByLoginAndPassword(String login, String password) throws DriverDAOException {
        Driver driver = null;

        try (Connection conn = Connector.getConnection();
             PreparedStatement prepSt = conn.prepareStatement(QUERY_SELECT_DRIVER_BY_LOGIN_AND_PASSWORD)) {
            prepSt.setString(1, login);
            prepSt.setString(2, password);
            ResultSet resultSet = prepSt.executeQuery();
            if (resultSet.next()) {
                logger.trace("driver " + resultSet.getString(2));
                driver = new Driver(
                        resultSet.getInt("id"),
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getInt("rating"),
                        resultSet.getInt("id_auto"),
                        resultSet.getInt("id_status")

                );
            } else {
                logger.debug(login + " " + password + " not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DriverDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new DriverDAOException();
        }
        return driver;
    }

    @Override
    public List<Driver> getListDriver() throws DriverDAOException {
        List<Driver> list = new ArrayList<>();
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DriverEntity> criteriaQuery = criteriaBuilder.createQuery(DriverEntity.class);
        Root<DriverEntity> root = criteriaQuery.from(DriverEntity.class);
        criteriaQuery.select(root);
        /*criteriaQuery.where(
                criteriaBuilder.and(
                )
        );*/
        List<DriverEntity> listDriverEntity = em.createQuery(criteriaQuery).getResultList();
        System.out.println(listDriverEntity +"size="+listDriverEntity.size());
        System.out.println(listDriverEntity.get(0).getFirstName());
        for (DriverEntity driver :
                listDriverEntity) {

            list.add(DriverMapper.converterToDriver(driver));
        }
         System.out.println();
        return list;

    }

    @Override
    public boolean addDriver(Driver driver) throws DriverDAOException {
        DriverEntity driverEntity = DriverMapper.converterToDriverEntity(driver);
        EntityManager em = FACTORY.createEntityManager();
        em.getTransaction().begin();
        em.merge(DriverMapper.converterToDriverEntity(driver));
        em.getTransaction().commit();
        logger.trace("Result insert "+em.contains(driverEntity));
        em.close();
        return true;
       /*try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = null;
            statement = conn.prepareStatement(QUERY_ADD_DRIVER);
            statement.setString(1, driver.getFirstName());
            statement.setString(2, driver.getLastName());
            statement.setString(3, driver.getPhoneNumber());
            statement.setString(4, driver.getLogin());
            statement.setString(5, driver.getPassword());
            statement.setInt(6, driver.getRating());
            statement.setInt(7, driver.getAuto());
            statement.setInt(8, driver.getStatus());
            int count = statement.executeUpdate();
            if (count > 0) {
                logger.trace("Add driver " + driver.getLastName() + " succesfull");
                return true;
            } else {
                logger.trace("Add  driver " + driver.getLastName() + " failed");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DriverDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new DriverDAOException();
        }
        return false;*/
    }

    @Override
    public Driver getDriverById(int id) throws DriverDAOException {
        Driver driver = null;
        DriverEntity driverEntities = driverRepository.findOne(id);

        /*EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DriverEntity> criteriaQuery = criteriaBuilder.createQuery(DriverEntity.class);
        Root<DriverEntity> root = criteriaQuery.from(DriverEntity.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), id)
                )
        );
        List<DriverEntity> listDriverEntity = em.createQuery(criteriaQuery).getResultList();
        driver = DriverMapper.converterToDriver(listDriverEntity.get(0));*/
        driver = DriverMapper.converterToDriver(driverEntities);
        return driver;
    }

    @Override
    public Driver getDriverByIdJoinAutoAndStatus(int id) throws DriverDAOException {
        Driver driver = null;
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(QUERY_SELECT_DRIVER_BY_ID_JOIN_AUTO_AND_STATUS);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                logger.trace("result select driver by id  " + result.getString("last_name"));
                driver = new Driver(
                        result.getInt(1),
                        result.getString(3),
                        result.getString(2),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getInt(7),
                        new Auto(
                                result.getInt(10),
                                result.getString(11),
                                result.getString(12),
                                result.getString(13),
                                result.getString(14)
                        ),
                        new Status(
                                result.getInt(15),
                                result.getString(16),
                                result.getString(17)
                        )
                );
            } else {
                logger.trace("select driver bi id=" + id + " failed");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DriverDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new DriverDAOException();
        }
        return driver;
    }

    @Override
    public boolean updateDriver(Driver driver) throws DriverDAOException {
        DriverEntity driverEntity = DriverMapper.converterToDriverEntity(driver);
        EntityManager em = FACTORY.createEntityManager();
        em.getTransaction().begin();
        em.merge(DriverMapper.converterToDriverEntity(driver));
        em.getTransaction().commit();
        System.out.println("Result update "+em.contains(driverEntity));
        em.close();
        return true;
       /* try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = null;
            statement = conn.prepareStatement(QUERY_UPDATE_DRIVER);
            statement.setString(1, driver.getFirstName());
            statement.setString(2, driver.getLastName());
            statement.setString(3, driver.getPhoneNumber());
            statement.setString(4, driver.getLogin());
            statement.setString(5, driver.getPassword());
            statement.setInt(6, driver.getRating());
            statement.setInt(7, driver.getAuto());
            statement.setInt(8, driver.getStatus());
            statement.setInt(9, driver.getId());

            int count = statement.executeUpdate();
            if (count > 0) {
                logger.trace("Update driver " + driver.getLastName() + " succesfull");
                return true;
            } else {
                logger.trace("Update  driver " + driver.getLastName() + " failed");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DriverDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new DriverDAOException();
        }
        return false;*/
    }

    @Override
    public boolean deleteDriverById(int id) throws DriverDAOException {
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement prepS = conn.prepareStatement(QUERY_DELETE_DRIVER_BY_ID);
            prepS.setInt(1, id);
            int count = prepS.executeUpdate();
            if (count > 0) {
                logger.trace("Delete successfull");
                return true;
            } else {
                logger.trace("Delete failed");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DriverDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new DriverDAOException();
        }
        return false;
    }

}
