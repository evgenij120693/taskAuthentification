package services;

import common.exception.DriverDAOException;
import models.dao.DriverDAO;
import models.pojo.Driver;

import java.util.List;

/**
 * Created by Шмыга on 25.02.2017.
 */
public class DriverService  {
    public static Driver getDriverByLoginAndPasswrod(String login, String password) throws DriverDAOException {
        return DriverDAO.getDriverByLoginAndPassword(login, password);
    }

    public static List<Driver> getListDriver() throws DriverDAOException {
        return DriverDAO.getListDriver();
    }

    public static boolean addDriver(Driver driver) throws DriverDAOException {
        return DriverDAO.addDriver(driver);
    }

    public static Driver getDriverById(int id) throws DriverDAOException {
        return DriverDAO.getDriverById(id);
    }

    public static boolean updateDriver(Driver driver) throws DriverDAOException {
        return DriverDAO.updateDriver(driver);
    }

    public static boolean deleteDriverById(int id) throws DriverDAOException {
        return DriverDAO.deleteDriverById(id);
    }
}
