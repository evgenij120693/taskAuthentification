package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.StatusDAOException;
import ru.svetozarov.models.dao.IStatusDAO;
import ru.svetozarov.models.pojo.Status;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
@Service(value = "statusService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StatusService implements IStatusService {
    private IStatusDAO IStatusDAO;
    @Override
    @Autowired
    @Qualifier("statusDAO")
    public void setStatusDAO(IStatusDAO IStatusDAO) {
        this.IStatusDAO = IStatusDAO;
    }

    @Override
    public  List<Status> getAllStatusDriver() throws StatusDAOException {
        return IStatusDAO.getAllStatusDriver();
    }
}
