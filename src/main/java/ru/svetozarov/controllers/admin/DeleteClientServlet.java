package ru.svetozarov.controllers.admin;

import ru.svetozarov.common.exception.ClientDAOException;
import org.apache.log4j.Logger;
import ru.svetozarov.services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Шмыга on 25.02.2017.
 */
public class DeleteClientServlet extends HttpServlet{
    private static Logger logger = Logger.getLogger(DeleteClientServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = (req.getParameter("id") != null)? Integer.valueOf(req.getParameter("id")):0;
        if(id != 0){
            try {
                if(ClientService.deleteClientById(id)){
                    resp.sendRedirect("/taxi/admin/list_client");
                }
            } catch (ClientDAOException e) {
                logger.error(e);
                resp.sendRedirect("/taxi/error.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
