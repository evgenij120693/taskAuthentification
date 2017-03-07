package ru.svetozarov.models.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.svetozarov.common.exception.AutoDAOException;
import ru.svetozarov.common.exception.ConnectorException;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.pojo.Auto;
import org.apache.log4j.Logger;

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

    @Override
    public  List<Auto> getAllAuto() throws AutoDAOException {
        List<Auto> list = new ArrayList<>();
        try (Connection conn = Connector.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL_AUTO);
            while (resultSet.next()){
                logger.trace("Select auto "+resultSet.getString("model"));
                Auto auto = new Auto(
                        resultSet.getInt("id"),
                        resultSet.getString("marka"),
                        resultSet.getString("model"),
                        resultSet.getString("reg_number"),
                        resultSet.getString("color")
                );
                list.add(auto);

            }
        } catch (SQLException e) {
            logger.error(e);
            throw new AutoDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new AutoDAOException();
        }
        return list;
    }

    @Override
    public  Auto getAutoById(int id) throws AutoDAOException {
        Auto auto = null;
        try (Connection conn = Connector.getConnection()) {
           PreparedStatement statement = conn.prepareStatement(QUERY_SELECT_AUTO_BY_ID);
           statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                logger.trace("Select auto by id="+id+" marka "+resultSet.getString("marka"));
                auto = new Auto(
                        resultSet.getInt("id"),
                        resultSet.getString("marka"),
                        resultSet.getString("model"),
                        resultSet.getString("reg_number"),
                        resultSet.getString("color")
                );
            }else{
                logger.trace("Select auto by id="+id+" marka "+resultSet.getString("marka")+" FAILED");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new AutoDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new AutoDAOException();
        }
        return auto;
    }

    @Override
    public  boolean updateAuto(Auto auto) throws AutoDAOException {
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE_AUTO);
            statement.setString(1, auto.getMarka());
            statement.setString(2, auto.getModel());
            statement.setString(3, auto.getRegNumber());
            statement.setString(4, auto.getColor());
            statement.setInt(5, auto.getId() );
           int count = statement.executeUpdate();
            if(count > 0){
                logger.trace("Update auto by id="+auto.getId()+" successful");
                return true;
            }else{
                logger.trace("Update auto by id="+auto.getId()+" FAILED");
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

    @Override
    public  boolean addAuto(Auto auto) throws AutoDAOException {
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(QUERY_ADD_AUTO);
            statement.setString(1, auto.getMarka());
            statement.setString(2, auto.getModel());
            statement.setString(3, auto.getRegNumber());
            statement.setString(4, auto.getColor());
            int count = statement.executeUpdate();
            if(count > 0){
                logger.trace("Add auto by id="+auto.getId()+" successful");
                return true;
            }else{
                logger.trace("Add auto by id="+auto.getId()+" FAILED");
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
