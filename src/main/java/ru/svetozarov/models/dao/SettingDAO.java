package ru.svetozarov.models.dao;

import ru.svetozarov.common.exception.ConnectorException;
import ru.svetozarov.common.exception.SettingDAOException;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.pojo.Setting;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Шмыга on 27.02.2017.
 */
public class SettingDAO {
    private static Logger logger = Logger.getLogger(SettingDAO.class);

    private static final String SQL_SELECT_SETTING = "select * from taxi.settings where id = 1";
    private static final String QUERY_UPDATE_SSETTING = "UPDATE taxi.setting set send_email=? where id=1";

    public static Setting getSetting() throws SettingDAOException {
        Setting setting = null;
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_SETTING)) {
            ResultSet resultSet = prepS.executeQuery();
            if (resultSet.next()) {
                setting = new Setting(resultSet.getInt(2));
            } else {
                logger.debug("Setting not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SettingDAOException();
        } catch (ConnectorException e) {
            logger.error(e.getMessage());
            throw new SettingDAOException();
        }
        return setting;
    }

    public static boolean updateSetting(Setting setting) throws SettingDAOException {
        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE_SSETTING);
            statement.setInt(1, setting.getSendMessageFlag());

            int count = statement.executeUpdate();
            if(count > 0){
                logger.trace("Update setting successful");
                return true;
            }else{
                logger.trace("Update setting FAILED");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new SettingDAOException();
        } catch (ConnectorException e) {
            logger.error(e);
            throw new SettingDAOException();
        }
        return false;
    }
}
