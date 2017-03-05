package ru.svetozarov.models.dao;

import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.pojo.User;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IUserDAO {
    boolean checkUserByLogin(String login) throws UserDAOException;

    boolean checkUserByLogin(String login, int id) throws UserDAOException;

    User getUserByLoginAndPassword(String login, String password) throws UserDAOException;
}
