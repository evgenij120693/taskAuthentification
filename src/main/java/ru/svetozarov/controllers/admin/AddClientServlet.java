package ru.svetozarov.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.models.pojo.Client;
import org.apache.log4j.Logger;
import ru.svetozarov.services.IClientService;
import ru.svetozarov.services.IUserService;

import javax.servlet.ServletConfig;
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

    private IUserService IUserService;
    @Autowired
    @Qualifier("userService")
    public void setUserService(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    private IClientService IClientService;
    @Autowired
    @Qualifier("clientService")
    public void setClientService(IClientService IClientService) {
        this.IClientService = IClientService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

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
            if (IUserService.checkUserByLogin(client.getLogin())) {
                if (IClientService.addClient(client)) {
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
