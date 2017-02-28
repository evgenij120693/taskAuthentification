package controllers;

import common.exception.ClientDAOException;
import common.exception.DriverDAOException;
import common.exception.UserDAOException;
import models.pojo.Admin;
import models.pojo.Client;
import models.pojo.Driver;
import org.apache.log4j.Logger;
import services.AdminService;
import services.ClientService;
import services.DriverService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Шмыга on 24.02.2017.
 */
public class LoginServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String role = "";
        int id = 0;
        try {
            Admin admin = AdminService.autorize(login, password);
            Client client = ClientService.getClientByLoginAndPassword(login, password);
            Driver driver = DriverService.getDriverByLoginAndPasswrod(login, password);
            if (admin != null) {
                setSession(admin.getId(), "admin", req);
                resp.sendRedirect("/taxi/admin");
            } else if (client != null) {
                setSession(client.getId(), "client", req);
                resp.sendRedirect("/taxi/client");
            } else if (driver != null) {
                setSession(driver.getId(), "driver", req);
                resp.sendRedirect("/taxi/driver");
            } else {
                logger.trace("Authorization failed");
                req.setAttribute("error", "Неверный логин/пароль.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                //resp.sendRedirect("/taxi/login");
            }
        } catch (UserDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        } catch (ClientDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        } catch (DriverDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }
    }
    private static void setSession(int id, String role, HttpServletRequest req){
        HttpSession session = req.getSession();
        session.setAttribute("id", id);
        session.setAttribute("role", role);
        session.setMaxInactiveInterval(30 * 60);
        logger.trace("Authorization successfull");
    }

}
