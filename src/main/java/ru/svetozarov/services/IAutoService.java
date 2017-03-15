package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import ru.svetozarov.common.exception.AutoDAOException;
import ru.svetozarov.models.dao.IAutoDAO;
import ru.svetozarov.models.pojo.Auto;

import java.util.List;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IAutoService {
    @Autowired
    @Qualifier("autoDAO")
    void setAutoDAO(IAutoDAO IAutoDAO);

    List<Auto> getAllAuto() throws AutoDAOException;

    @Secured("ROLE_ADMIN")
    Auto getAutoById(int id) throws AutoDAOException;

    @Secured("ROLE_ADMIN")
    boolean updateAuto(Auto auto) throws AutoDAOException;

    @Secured("ROLE_ADMIN")
    boolean addAuto(Auto auto) throws AutoDAOException;

    @Secured("ROLE_ADMIN")
    boolean deleteAuto(int id) throws AutoDAOException;
}
