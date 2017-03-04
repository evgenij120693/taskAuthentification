package ru.svetozarov.services;

import ru.svetozarov.common.exception.StatusDAOException;
import ru.svetozarov.models.dao.StatusDAO;
import ru.svetozarov.models.pojo.Status;

import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
public class StatusService {
    public static List<Status> getAllStatusDriver () throws StatusDAOException {
        return StatusDAO.getAllStatusDriver();
    }
}
