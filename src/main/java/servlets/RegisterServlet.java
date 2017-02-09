package servlets;

import com.api.Factory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by dima on 07.02.17.
 */
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(true);

        try {
            Factory.getInstance().getUserDAO().add(userName,password);
            response.sendRedirect("login_yes.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //response.sendRedirect("login_yes.jsp");
    }
}