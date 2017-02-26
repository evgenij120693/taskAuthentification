package services;

import common.exception.UserDAOException;
import models.dao.UserDAO;

/**
 * Created by Шмыга on 25.02.2017.
 */
public class UserService {
    public static boolean checkUserByLogin(String login) throws UserDAOException {
        return UserDAO.checkUserByLogin(login);
    }
    public static boolean checkUserByLoginAndId(String login, int id) throws UserDAOException {
        return UserDAO.checkUserByLogin(login, id);
    }


}
