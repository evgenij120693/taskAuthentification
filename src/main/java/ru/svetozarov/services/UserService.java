package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.dao.UserDAO;
import ru.svetozarov.models.pojo.User;

/**
 * Created by Шмыга on 25.02.2017.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public  boolean checkUserByLogin(String login) throws UserDAOException {
        return userDAO.checkUserByLogin(login);
    }
    public  boolean checkUserByLoginAndId(String login, int id) throws UserDAOException {
        return userDAO.checkUserByLogin(login, id);
    }

    public  User getUserByLoginAndPassword(String login, String password) throws UserDAOException {
        return userDAO.getUserByLoginAndPassword(login, password);
    }


}
