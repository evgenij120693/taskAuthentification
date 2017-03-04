package ru.svetozarov.controllers.listener;

import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.common.util.SenderMail;
import ru.svetozarov.models.dao.AdminDAO;
import ru.svetozarov.models.pojo.Admin;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Шмыга on 27.02.2017.
 */
public class SessionListeners implements HttpSessionListener, HttpSessionAttributeListener {

    private static Logger logger = Logger.getLogger(SessionListeners.class);
    private int id;

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        if (event.getName().equals("id")) {
            this.id = (int) event.getValue();
        }
        if (event.getName().equals("role") && event.getValue().equals("admin")) {
            try {
                logger.trace("send messag");
                Admin admin = AdminDAO.getAdminById((Integer) id);
                if (admin.getSendEmailFlag() != 0) {
                    SenderMail.sendMail(admin.getEmail(), "Авторизация в системе 'Такси онлайн'",
                            "Вы авторизовались в системе");
                }
            } catch (UserDAOException e) {
                logger.error(e);
            }
            logger.trace(event.getName());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
