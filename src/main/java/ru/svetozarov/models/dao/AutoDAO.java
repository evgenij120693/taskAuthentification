package ru.svetozarov.models.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.svetozarov.common.exception.AutoDAOException;
import ru.svetozarov.common.exception.ConnectorException;
import ru.svetozarov.common.util.Factory;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.entity.AutoEntity;
import ru.svetozarov.models.mapper_entity.AutoMapper;
import ru.svetozarov.models.pojo.Auto;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шмыга on 26.02.2017.
 */
@Repository(value = "autoDAO")
public class AutoDAO implements IAutoDAO {
    private static Logger logger = Logger.getLogger(AutoDAO.class);
    private  final String QUERY_SELECT_ALL_AUTO = "select * from taxi.auto";
    private  final String QUERY_SELECT_AUTO_BY_ID = "select * from taxi.auto WHERE id=?";
    private  final String QUERY_UPDATE_AUTO = "update taxi.auto set marka=?, model=?, reg_number=?, " +
            "color=? where id=?";
    private  final String QUERY_ADD_AUTO = "insert into taxi.auto (marka, model, reg_number, color) " +
            "VALUE (?,?,?,?)";
    private  final String QUERY_DELETE_AUTO_BY_ID = "delete from taxi.auto where id=?";

    private static final EntityManagerFactory FACTORY =
            Factory.getFACTORY();

    @Override
    public  List<Auto> getAllAuto() throws AutoDAOException {
        List<Auto> list = new ArrayList<>();
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<AutoEntity> criteriaQuery = criteriaBuilder.createQuery(AutoEntity.class);
        Root<AutoEntity> root = criteriaQuery.from(AutoEntity.class);
        criteriaQuery.select(root);
        /*criteriaQuery.where(
                criteriaBuilder.and(
                )
        );*/
        List<AutoEntity> listAutoEntity = em.createQuery(criteriaQuery).getResultList();

        for (AutoEntity auto :
                listAutoEntity) {

            list.add(AutoMapper.converterToAuto(auto));
        }
        return list;
    }

    @Override
    public  Auto getAutoById(int id) throws AutoDAOException {
        Auto auto = null;
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<AutoEntity> criteriaQuery = criteriaBuilder.createQuery(AutoEntity.class);
        Root<AutoEntity> root = criteriaQuery.from(AutoEntity.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), id)
                )
        );
        List<AutoEntity> listAutoEntity = em.createQuery(criteriaQuery).getResultList();
        auto = AutoMapper.converterToAuto(listAutoEntity.get(0));
        return auto;
    }

    @Override
    public  boolean updateAuto(Auto auto) throws AutoDAOException {
        AutoEntity autoEntity = AutoMapper.converterToAutoEntity(auto);
        EntityManager em = FACTORY.createEntityManager();
        em.getTransaction().begin();
        em.merge(autoEntity);
        em.getTransaction().commit();
        logger.trace("Result update client"+em.contains(autoEntity));
        em.close();
        return true;
    }

    @Override
    public  boolean addAuto(Auto auto) throws AutoDAOException {
        AutoEntity autoEntity = AutoMapper.converterToAutoEntity(auto);
        EntityManager em = FACTORY.createEntityManager();
        em.getTransaction().begin();
        em.merge(autoEntity);
        em.getTransaction().commit();
        logger.trace("Result add auto"+em.contains(autoEntity));
        em.close();
        return true;
    }

    @Override
    public  boolean deleteAutoById(int id) throws AutoDAOException {
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement prepS = conn.prepareStatement(QUERY_DELETE_AUTO_BY_ID);
            prepS.setInt(1, id);
            int count  = prepS.executeUpdate();
            if (count > 0){
                logger.trace("Delete successfull");
                return true;
            }else{
                logger.trace("Delete failed");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new AutoDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new AutoDAOException();
        }
        return false;
    }
}
