package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import ru.svetozarov.common.exception.StatusDAOException;
import ru.svetozarov.models.dao.IStatusDAO;
import ru.svetozarov.models.pojo.Status;

import java.util.List;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IStatusService {
    @Autowired
    @Qualifier("statusDAO")
    void setStatusDAO(IStatusDAO IStatusDAO);

    @Secured({"ROLE_DRIVER", "ROLE_USER", "ROLE_DRIVER"})
    List<Status> getAllStatusDriver() throws StatusDAOException;
}
