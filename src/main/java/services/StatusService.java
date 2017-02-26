package services;

import common.exception.StatusDAOException;
import models.dao.StatusDAO;
import models.pojo.Status;

import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
public class StatusService {
    public static List<Status> getAllStatusDriver () throws StatusDAOException {
        return StatusDAO.getAllStatusDriver();
    }
}
