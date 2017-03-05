package ru.svetozarov.models.dao;

import ru.svetozarov.common.exception.DriverDAOException;
import ru.svetozarov.models.pojo.Driver;

import java.util.List;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IDriverDAO {
    Driver getDriverByLoginAndPassword(String login, String password) throws DriverDAOException;

    List<Driver> getListDriver() throws DriverDAOException;

    boolean addDriver(Driver driver) throws DriverDAOException;

    Driver getDriverById(int id) throws DriverDAOException;

    Driver getDriverByIdJoinAutoAndStatus(int id) throws DriverDAOException;

    boolean updateDriver(Driver driver) throws DriverDAOException;

    boolean deleteDriverById(int id) throws DriverDAOException;
}
