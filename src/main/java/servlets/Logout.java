package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dima on 21.02.17.
 */
public class Logout extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/login.jsp");
    }
}
