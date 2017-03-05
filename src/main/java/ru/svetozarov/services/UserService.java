package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.dao.IUserDAO;
import ru.svetozarov.models.pojo.User;

import javax.annotation.Resource;

/**
 * Created by Шмыга on 25.02.2017.
 */
@Service(value = "userService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserService implements IUserService {
    private IUserDAO IUserDAO;
    @Override
    @Autowired
    @Qualifier("userDAO")
    public void setUserDAO(IUserDAO IUserDAO) {
        this.IUserDAO = IUserDAO;
    }

    @Override
    public  boolean checkUserByLogin(String login) throws UserDAOException {
        return IUserDAO.checkUserByLogin(login);
    }
    @Override
    public  boolean checkUserByLoginAndId(String login, int id) throws UserDAOException {
        return IUserDAO.checkUserByLogin(login, id);
    }

    @Override
    public  User getUserByLoginAndPassword(String login, String password) throws UserDAOException {
        return IUserDAO.getUserByLoginAndPassword(login, password);
    }


}
