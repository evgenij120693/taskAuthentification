package ru.svetozarov.models.dao;

import ru.svetozarov.common.exception.StatusDAOException;
import ru.svetozarov.models.pojo.Status;

import java.util.List;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IStatusDAO {
    List<Status> getAllStatusDriver() throws StatusDAOException;

    Status getStatusOrderById(int id) throws StatusDAOException;
}
