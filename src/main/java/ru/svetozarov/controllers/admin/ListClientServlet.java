package ru.svetozarov.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.svetozarov.common.exception.ClientDAOException;
import ru.svetozarov.controllers.LoginServlet;
import ru.svetozarov.models.pojo.Client;
import org.apache.log4j.Logger;
import ru.svetozarov.services.ClientService;

import javax.servlet.ServletConfig;
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

    private ClientService clientService;
    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            List<Client> list = clientService.getAllClients();
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
