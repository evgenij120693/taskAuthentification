package ru.svetozarov.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.models.pojo.Client;
import org.apache.log4j.Logger;
import ru.svetozarov.services.IClientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Шмыга on 28.02.2017.
 */
public class EditClientServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EditClientServlet.class);

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
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");
        if (id != 0) {
            try {
                Client client = IClientService.getClientBiId(id);
                if (client != null) {
                    req.setAttribute("client", client);
                    req.getRequestDispatcher("/client/edit.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("/taxi/client/");
                }
            } catch (ClientDAOException e) {
                logger.error(e);
                resp.sendRedirect("/taxi/error.jsp");
            }
        } else {
            resp.sendRedirect("/taxi/client");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = "client";
        String greetings = "";
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute("id");

        try {
            Client temp = IClientService.getClientBiId(id);
            Client client = new Client(
                    id,
                    req.getParameter("name"),
                    req.getParameter("sex"),
                    req.getParameter("phone"),
                    req.getParameter("email"),
                    temp.getLogin(),
                    req.getParameter("password")
            );

            if (IClientService.updateClient(client)) {
                logger.trace("Client " + client.getName() + " updated");
                resp.sendRedirect("/taxi/client");
            } else {
                logger.trace("Error update client");
                resp.sendRedirect("/taxi/client/edit");
            }
        } catch (ClientDAOException e) {
            logger.error(e);
            resp.sendRedirect("/taxi/error.jsp");
        }
    }
}
