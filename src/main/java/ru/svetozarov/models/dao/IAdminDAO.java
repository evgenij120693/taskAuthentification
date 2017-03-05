package ru.svetozarov.models.dao;

import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.pojo.Admin;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IAdminDAO {
    Admin getAdminByLoginAndPassword(String login, String password)
            throws UserDAOException;

    Admin getAdminById(int id) throws UserDAOException;

    boolean updateAdmin(Admin admin) throws UserDAOException;
}
