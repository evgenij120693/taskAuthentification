package ru.svetozarov.services;

import ru.svetozarov.common.exception.AutoDAOException;
import ru.svetozarov.models.dao.AutoDAO;
import ru.svetozarov.models.pojo.Auto;

import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
public class AutoService {
    public static List<Auto> getAllAuto() throws AutoDAOException {
        return AutoDAO.getAllAuto();
    }

    public static Auto getAutoById(int id) throws AutoDAOException {
        return AutoDAO.getAutoById(id);
    }

    public static boolean updateAuto(Auto auto) throws AutoDAOException {
        return AutoDAO.updateAuto(auto);
    }

    public static boolean addAuto(Auto auto) throws AutoDAOException {
        return AutoDAO.addAuto(auto);
    }

    public static boolean deleteAuto(int id) throws AutoDAOException {
        return AutoDAO.deleteAutoById(id);
    }
}
