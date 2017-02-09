package servlets;

import com.api.Factory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by dima on 09.02.17.
 */
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException{
        String userName = request.getParameter("userName");
        String password = request.getParameter("passwolrd");

        if (userName != null && !userName.isEmpty()
                && password!= null && !password.isEmpty()){
            try {
                boolean inBase = Factory.getInstance().getUserDAO().check(userName, password);
                if (inBase){
                    request.getSession().setAttribute("userName",userName);
                    response.sendRedirect("./user/ModelNoEmployee.jsp");
                }else{
                    response.sendRedirect("login_error.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            response.sendRedirect("login_error.jsp");
        }


    }
}
