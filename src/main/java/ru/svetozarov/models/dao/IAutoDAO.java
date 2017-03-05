package ru.svetozarov.models.dao;

import ru.svetozarov.common.exception.AutoDAOException;
import ru.svetozarov.models.pojo.Auto;

import java.util.List;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IAutoDAO {
    List<Auto> getAllAuto() throws AutoDAOException;

    Auto getAutoById(int id) throws AutoDAOException;

    boolean updateAuto(Auto auto) throws AutoDAOException;

    boolean addAuto(Auto auto) throws AutoDAOException;

    boolean deleteAutoById(int id) throws AutoDAOException;
}
