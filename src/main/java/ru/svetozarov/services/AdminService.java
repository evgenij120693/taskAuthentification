package ru.svetozarov.services;

import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.dao.AdminDAO;
import ru.svetozarov.models.pojo.Admin;

/**
 * Created by Шмыга on 24.02.2017.
 */
public class AdminService {
    public static Admin autorize(String login, String password) throws UserDAOException {
        return AdminDAO.getAdminByLoginAndPassword(login, password);
    }

    /*public static int addUser(String login, String password, String role) throws UserDAOException {
        return AdminDAO.addUser(login, password, role);
    }*/
    public static boolean updateAdmin(Admin admin) throws UserDAOException {
        return AdminDAO.updateAdmin(admin);
    }

    public static Admin getAdminById(int id) throws UserDAOException {
        return AdminDAO.getAdminById(id);
    }
}
