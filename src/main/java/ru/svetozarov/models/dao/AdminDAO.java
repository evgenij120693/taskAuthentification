package ru.svetozarov.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.common.exception.ConnectorException;
import ru.svetozarov.common.util.Factory;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.entity.AdminEntity;
import ru.svetozarov.models.entity.AdminEntity;
import ru.svetozarov.models.mapper_entity.AdminMapper;
import ru.svetozarov.models.pojo.Admin;
import org.apache.log4j.Logger;
import ru.svetozarov.models.repository.AdminRepository;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Шмыга on 24.02.2017.
 */
@Transactional
@Repository(value = "adminDAO")
public class AdminDAO implements IAdminDAO {

    AdminRepository adminRepository;

    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    private static Logger logger = Logger.getLogger(AdminDAO.class);
    private static final EntityManagerFactory FACTORY =
            Factory.getFACTORY();

    private final String SQL_FIND_USER = "Select * from taxi.admin where login = ? and password = ?";
    private final String SQL_ADMIN_BY_LOGIN = "Select * from taxi.admin where login = ?";
    private final String SQL_ADMIN_BY_ID = "Select * from taxi.admin where id= ?";
    private final String SQL_UPDATE_FLAG_SEND_MAIL = "update taxi.admin set send_mail_flag=?" +
            " where id=?";
    private final String SQL_UPDATE_ADMIN = "update taxi.admin set password=?, name=?, " +
            " email=?, send_email_flag=? where id=?";

    private final String SQL_INSERT_USER = "Insert into taxi.admin (login, password, name)" +
            " values (?, ?, ?)";

    @Override
    public Admin getAdminByLoginAndPassword(String login, String password)
            throws UserDAOException {
        Admin admin = null;
        List<AdminEntity> list = adminRepository.findByLoginAndPassword(login, password);
        admin = AdminMapper.converterToAdmin(list.get(0));
        return admin;
    }


    private boolean checkUserByLogin(String login) throws UserDAOException {
        Admin admin = null;
        List<AdminEntity> list = adminRepository.findByLogin(login);
        admin = AdminMapper.converterToAdmin(list.get(0));
        return (admin == null) ? true : false;
    }

    @Override
    public Admin getAdminById(int id) throws UserDAOException {
        Admin admin = null;
        AdminEntity driverEntities = adminRepository.findOne(id);
        admin = AdminMapper.converterToAdmin(driverEntities);
        return admin;
    }

    @Override
    public boolean updateAdmin(Admin admin) throws UserDAOException {
        AdminEntity adminEntity = AdminMapper.converterToAdminEntity(admin);
        adminRepository.save(adminEntity);
        return true;
    }

}
