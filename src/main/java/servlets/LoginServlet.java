package servlets;

import com.api.Factory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dima on 09.02.17.
 */
public class LoginServlet extends HttpServlet {
    private final String messageError = " <script>\n" +
            "        window.onload = function() {\n" +
            "            alert( \"Sorry, you are wrong, try log in again or sign up\" );\n" +
            "        };\n" +
            "    </script>";

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException{
        String userName = request.getParameter("userName").trim();
        String password = request.getParameter("password").trim();

        if (userName != null && !userName.isEmpty()
                && password!= null && !password.isEmpty()){
            try {
                boolean inBase = Factory.getInstance().getUserDAO().checkUserName(userName);
                List modelList = Factory.getInstance().getModelNameDAO().getAll();
                if (inBase){
                    request.getSession().setAttribute("userName",userName);
                    request.getSession().setAttribute("modelList",modelList);
                    response.sendRedirect("./user/ModelNoEmployee.jsp");
                }else{
                    request.getSession().invalidate();
                    request.getSession().setAttribute("errorMessage", messageError);
                    response.sendRedirect("login.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            request.getSession().invalidate();
            request.getSession().setAttribute("errorMessage", messageError);
            response.sendRedirect("login.jsp");
        }
    }
}
