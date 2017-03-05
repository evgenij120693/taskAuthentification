package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.DriverDAOException;
import ru.svetozarov.models.dao.IDriverDAO;
import ru.svetozarov.models.pojo.Driver;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Шмыга on 25.02.2017.
 */
@Service(value = "driverService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DriverService implements IDriverService {

    private IDriverDAO IDriverDAO;
    @Override
    @Autowired
    @Qualifier("driverDAO")
    public void setDriverDAO(IDriverDAO IDriverDAO) {
        this.IDriverDAO = IDriverDAO;
    }

    @Override
    public  Driver getDriverByLoginAndPasswrod(String login, String password) throws DriverDAOException {
        return IDriverDAO.getDriverByLoginAndPassword(login, password);
    }

    @Override
    public  List<Driver> getListDriver() throws DriverDAOException {
        return IDriverDAO.getListDriver();
    }


    @Override
    public  boolean addDriver(Driver driver) throws DriverDAOException {
        return IDriverDAO.addDriver(driver);
    }

    @Override
    public  Driver getDriverById(int id) throws DriverDAOException {
        return IDriverDAO.getDriverById(id);
    }

    @Override
    public  boolean updateDriver(Driver driver) throws DriverDAOException {
        return IDriverDAO.updateDriver(driver);
    }

    @Override
    public  boolean deleteDriverById(int id) throws DriverDAOException {
        return IDriverDAO.deleteDriverById(id);
    }

    @Override
    public  Driver getDriverByIdJoinAutoAndStatus(int id) throws DriverDAOException {
        return IDriverDAO.getDriverByIdJoinAutoAndStatus(id);
    }
}
