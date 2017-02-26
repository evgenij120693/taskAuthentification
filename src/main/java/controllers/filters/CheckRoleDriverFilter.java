package controllers.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Шмыга on 26.02.2017.
 */
public class CheckRoleDriverFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        res.setDateHeader("Expires", 0);
        HttpSession session = req.getSession(false);
        if(session == null ){
            res.sendRedirect("/taxi/login");
        }else{
            if(session.getAttribute("role") == null){
                res.sendRedirect("/taxi/login");
            }else{
                if(!session.getAttribute("role").equals("driver")){
                    res.sendRedirect("/taxi/"+session.getAttribute("role"));
                }else {
                    chain.doFilter(request, response);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
