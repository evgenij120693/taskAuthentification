package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import ru.svetozarov.common.exception.DriverDAOException;
import ru.svetozarov.models.dao.IDriverDAO;
import ru.svetozarov.models.pojo.Driver;

import java.util.List;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IDriverService {
    @Autowired
    @Qualifier("driverDAO")
    void setDriverDAO(IDriverDAO IDriverDAO);

    @Secured("ROLE_ANONYMOUS")
    Driver getDriverByLoginAndPasswrod(String login, String password) throws DriverDAOException;

    @Secured("ROLE_ADMIN")
    List<Driver> getListDriver() throws DriverDAOException;

    @Secured("ROLE_ADMIN")
    boolean addDriver(Driver driver) throws DriverDAOException;

    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_DRIVER"})
    Driver getDriverById(int id) throws DriverDAOException;

    @Secured({"ROLE_ADMIN", "ROLE_DRIVER"})
    boolean updateDriver(Driver driver) throws DriverDAOException;

    @Secured("ROLE_ADMIN")
    boolean deleteDriverById(int id) throws DriverDAOException;

    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_DRIVER"})
    Driver getDriverByIdJoinAutoAndStatus(int id) throws DriverDAOException;
}
