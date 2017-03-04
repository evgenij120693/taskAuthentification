package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.AutoDAOException;
import ru.svetozarov.models.dao.AutoDAO;
import ru.svetozarov.models.pojo.Auto;

import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AutoService {

    private AutoDAO autoDAO;
    @Autowired
    public void setAutoDAO(AutoDAO autoDAO) {
        this.autoDAO = autoDAO;
    }

    public  List<Auto> getAllAuto() throws AutoDAOException {
        return autoDAO.getAllAuto();
    }

    public  Auto getAutoById(int id) throws AutoDAOException {
        return autoDAO.getAutoById(id);
    }

    public  boolean updateAuto(Auto auto) throws AutoDAOException {
        return autoDAO.updateAuto(auto);
    }

    public  boolean addAuto(Auto auto) throws AutoDAOException {
        return autoDAO.addAuto(auto);
    }

    public  boolean deleteAuto(int id) throws AutoDAOException {
        return autoDAO.deleteAutoById(id);
    }
}
