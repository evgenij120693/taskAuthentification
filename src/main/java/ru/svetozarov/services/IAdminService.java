package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.dao.IAdminDAO;
import ru.svetozarov.models.pojo.Admin;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IAdminService {
    @Autowired
    @Qualifier("adminDAO")
    void setAdminDAO(IAdminDAO IAdminDAO);

    Admin autorize(String login, String password) throws UserDAOException;

    /*public  int addUser(String login, String password, String role) throws UserDAOException {
            return AdminDAO.addUser(login, password, role);
        }*/
    @Secured("ROLE_ADMIN")
    boolean updateAdmin(Admin admin) throws UserDAOException;

    @Secured("ROLE_ADMIN")
    Admin getAdminById(int id) throws UserDAOException;
}
