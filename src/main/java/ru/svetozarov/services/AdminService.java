package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.dao.AdminDAO;
import ru.svetozarov.models.pojo.Admin;

/**
 * Created by Шмыга on 24.02.2017.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AdminService {
    private AdminDAO adminDAO;
    @Autowired
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public  Admin autorize(String login, String password) throws UserDAOException {
        return adminDAO.getAdminByLoginAndPassword(login, password);
    }

    /*public  int addUser(String login, String password, String role) throws UserDAOException {
        return AdminDAO.addUser(login, password, role);
    }*/
    public  boolean updateAdmin(Admin admin) throws UserDAOException {
        return adminDAO.updateAdmin(admin);
    }

    public  Admin getAdminById(int id) throws UserDAOException {
        return adminDAO.getAdminById(id);
    }
}
