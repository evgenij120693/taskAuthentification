package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.StatusDAOException;
import ru.svetozarov.models.dao.StatusDAO;
import ru.svetozarov.models.pojo.Status;

import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StatusService {
    private StatusDAO statusDAO;
    @Autowired
    public void setStatusDAO(StatusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }

    public  List<Status> getAllStatusDriver () throws StatusDAOException {
        return statusDAO.getAllStatusDriver();
    }
}
