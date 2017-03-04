package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.DriverDAOException;
import ru.svetozarov.models.dao.DriverDAO;
import ru.svetozarov.models.pojo.Driver;

import java.util.List;

/**
 * Created by Шмыга on 25.02.2017.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DriverService  {

    private DriverDAO driverDAO;
    @Autowired
    public void setDriverDAO(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    public  Driver getDriverByLoginAndPasswrod(String login, String password) throws DriverDAOException {
        return driverDAO.getDriverByLoginAndPassword(login, password);
    }

    public  List<Driver> getListDriver() throws DriverDAOException {
        return driverDAO.getListDriver();
    }


    public  boolean addDriver(Driver driver) throws DriverDAOException {
        return driverDAO.addDriver(driver);
    }

    public  Driver getDriverById(int id) throws DriverDAOException {
        return driverDAO.getDriverById(id);
    }

    public  boolean updateDriver(Driver driver) throws DriverDAOException {
        return driverDAO.updateDriver(driver);
    }

    public  boolean deleteDriverById(int id) throws DriverDAOException {
        return driverDAO.deleteDriverById(id);
    }

    public  Driver getDriverByIdJoinAutoAndStatus(int id) throws DriverDAOException {
        return driverDAO.getDriverByIdJoinAutoAndStatus(id);
    }
}
