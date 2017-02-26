package services;

import common.exception.UserDAOException;
import models.dao.AdminDAO;
import models.pojo.Admin;

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
}
