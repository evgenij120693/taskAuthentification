package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.dao.IUserDAO;
import ru.svetozarov.models.pojo.User;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IUserService {
    @Autowired
    @Qualifier("userDAO")
    void setUserDAO(IUserDAO IUserDAO);

    @Secured({"ROLE_ADMIN", "ROLE_USER", "ADMIN_DRIVER"})
    boolean checkUserByLogin(String login) throws UserDAOException;

    @Secured({"ROLE_ADMIN", "ROLE_USER", "ADMIN_DRIVER"})
    boolean checkUserByLoginAndId(String login, int id) throws UserDAOException;


    User getUserByLoginAndPassword(String login, String password) throws UserDAOException;


    User getUserByLogin(String login) throws UserDAOException;

}
