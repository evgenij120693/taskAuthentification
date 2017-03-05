package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    Auto getAutoById(int id) throws AutoDAOException;

    boolean updateAuto(Auto auto) throws AutoDAOException;

    boolean addAuto(Auto auto) throws AutoDAOException;

    boolean deleteAuto(int id) throws AutoDAOException;
}
