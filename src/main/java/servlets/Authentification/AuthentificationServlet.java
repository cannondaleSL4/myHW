package servlets.Authentification;

import com.api.Factory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by dima on 05.02.17.
 */
public class AuthentificationServlet extends HttpServlet {

    protected  void doPost(HttpServletRequest request,
                           HttpServletResponse response)throws ServletException, IOException{
        String username = request.getParameter("userName");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(true);

        try{
            Factory.getInstance().getUserDAO().add(username,password);
            response.sendRedirect("Success");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
