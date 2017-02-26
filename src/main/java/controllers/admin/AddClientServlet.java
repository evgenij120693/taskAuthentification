package controllers.admin;

import common.exception.ClientDAOException;
import common.exception.UserDAOException;
import models.pojo.Client;
import org.apache.log4j.Logger;
import services.ClientService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Шмыга on 25.02.2017.
 */
public class AddClientServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AddClientServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/add_client.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = "client";
        String greetings = "";
        Client client = new Client(
                0,
                req.getParameter("name"),
                req.getParameter("sex"),
                req.getParameter("phone"),
                req.getParameter("email"),
                req.getParameter("login"),
                req.getParameter("password")
        );

        try {
            if (UserService.checkUserByLogin(client.getLogin())) {
                if (ClientService.addClient(client)) {
                    logger.trace("Client " + client.getName() + " added");
                    resp.sendRedirect("/taxi/admin/list_client");
                } else {
                    logger.trace("Dublicate client");
                    resp.sendRedirect("/taxi/admin/add_client");
                }
            } else  {
                resp.sendRedirect("/taxi/admin/add_client");
            }
        } catch (UserDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        } catch (ClientDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }
       /* resp.setContentType("text/plain");
        resp.getWriter().write(greetings);*/
    }
}
