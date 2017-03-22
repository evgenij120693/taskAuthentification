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
import java.util.Set;

/**
 * Created by Шмыга on 25.02.2017.
 */
@Transactional
@Repository(value = "driverDAO")

public class DriverDAO implements IDriverDAO {

    private static Logger logger = Logger.getLogger(DriverDAO.class);

    private static final EntityManagerFactory FACTORY =
           Factory.getFACTORY();


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
        List<Driver> list = new ArrayList<>();

        Iterable<DriverEntity> listDriverEntity = driverRepository.findByLoginAndPassword(login, password);;
        for (DriverEntity driverr :
                listDriverEntity) {

            list.add(DriverMapper.converterToDriver(driverr));
        }
        return list.get(0);
    }

    @Override
    public List<Driver> getListDriver() throws DriverDAOException {
        List<Driver> list = new ArrayList<>();
        Iterable<DriverEntity> listDriverEntity = driverRepository.findAll();
        for (DriverEntity driver :
                listDriverEntity) {

            list.add(DriverMapper.converterToDriver(driver));
        }
        return list;
    }

    @Override
    public boolean addDriver(Driver driver) throws DriverDAOException {
        DriverEntity driverEntity = DriverMapper.converterToDriverEntity(driver);
        driverRepository.save(driverEntity);
        return true;
    }

    @Override
    public Driver getDriverById(int id) throws DriverDAOException {
        Driver driver = null;
        DriverEntity driverEntities = driverRepository.findOne(id);
        driver = DriverMapper.converterToDriver(driverEntities);
        return driver;
    }

    @Override
    public Driver getDriverByIdJoinAutoAndStatus(int id) throws DriverDAOException {
        Driver driver = null;
        DriverEntity driverEntities = driverRepository.findOne(id);
        driver = DriverMapper.converterToDriver(driverEntities);
        return driver;
    }

    @Override
    public boolean updateDriver(Driver driver) throws DriverDAOException {
        DriverEntity driverEntity = DriverMapper.converterToDriverEntity(driver);
        driverRepository.save(driverEntity);
        return true;

    }

    @Override
    public boolean deleteDriverById(int id) throws DriverDAOException {
        driverRepository.delete(id);
        return true;
    }

}
