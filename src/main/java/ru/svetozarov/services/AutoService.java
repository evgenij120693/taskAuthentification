package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.AutoDAOException;
import ru.svetozarov.models.dao.IAutoDAO;
import ru.svetozarov.models.pojo.Auto;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
@Service(value = "autoService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AutoService implements IAutoService {

    private IAutoDAO IAutoDAO;
    @Override
    @Autowired
    @Qualifier("autoDAO")
    public void setAutoDAO(IAutoDAO IAutoDAO) {
        this.IAutoDAO = IAutoDAO;
    }

    @Override
    public  List<Auto> getAllAuto() throws AutoDAOException {
        return IAutoDAO.getAllAuto();
    }

    @Override
    public  Auto getAutoById(int id) throws AutoDAOException {
        return IAutoDAO.getAutoById(id);
    }

    @Override
    public  boolean updateAuto(Auto auto) throws AutoDAOException {
        return IAutoDAO.updateAuto(auto);
    }

    @Override
    public  boolean addAuto(Auto auto) throws AutoDAOException {
        return IAutoDAO.addAuto(auto);
    }

    @Override
    public  boolean deleteAuto(int id) throws AutoDAOException {
        return IAutoDAO.deleteAutoById(id);
    }
}
