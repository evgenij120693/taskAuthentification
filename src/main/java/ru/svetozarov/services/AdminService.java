package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.dao.IAdminDAO;
import ru.svetozarov.models.pojo.Admin;

import javax.annotation.Resource;

/**
 * Created by Шмыга on 24.02.2017.
 */
@Service(value = "adminService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AdminService implements IAdminService {
    private IAdminDAO IAdminDAO;
    @Override
    @Autowired
    @Qualifier("adminDAO")
    public void setAdminDAO(IAdminDAO IAdminDAO) {
        this.IAdminDAO = IAdminDAO;
    }

    @Override
    public  Admin autorize(String login, String password) throws UserDAOException {
        return IAdminDAO.getAdminByLoginAndPassword(login, password);
    }

    /*public  int addUser(String login, String password, String role) throws UserDAOException {
        return AdminDAO.addUser(login, password, role);
    }*/
    @Override
    public  boolean updateAdmin(Admin admin) throws UserDAOException {
        return IAdminDAO.updateAdmin(admin);
    }

    @Override
    public  Admin getAdminById(int id) throws UserDAOException {
        return IAdminDAO.getAdminById(id);
    }
}
