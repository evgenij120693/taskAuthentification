package controllers.admin;

import common.exception.ClientDAOException;
import controllers.LoginServlet;
import models.pojo.Client;
import org.apache.log4j.Logger;
import services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Шмыга on 25.02.2017.
 */
public class ListClientServlet extends HttpServlet{
    private static Logger logger = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            List<Client> list = ClientService.getAllClients();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/admin/list_client.jsp").forward(req, resp);
        } catch (ClientDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
