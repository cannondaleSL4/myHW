package servlets;

import com.api.Factory;
import com.authentification.userEntity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dima on 07.02.17.
 */
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final String messageError = " <script>\n" +
            "        window.onload = function() {\n" +
            "            alert( \"Sorry some error try again please\" );\n" +
            "        };\n" +
            "    </script>";

    private final String messageSuccess = " <script>\n" +
            "        window.onload = function() {\n" +
            "            alert( \"You have successfully signed up\" );\n" +
            "        };\n" +
            "    </script>";

    private final String messageBusy = " <script>\n" +
            "        window.onload = function() {\n" +
            "            alert( \"Sorry, this login is busy, try some any\" );\n" +
            "        };\n" +
            "    </script>";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("userName").trim();
        String password = request.getParameter("password").trim();



        if (userName != null && !userName.isEmpty()
                && password!= null && !password.isEmpty()){
            try {
                boolean inBase = Factory.getInstance().getUserDAO().checkUserName(userName);
                if (!inBase){
                    Factory.getInstance().getUserDAO().add(userName,password);
                    request.getSession().setAttribute("messageSuccess", messageSuccess);
                    response.sendRedirect("login.jsp");
                }else{
                    request.getSession().invalidate();
                    request.getSession().setAttribute("errorMessage", messageBusy);
                    response.sendRedirect("register.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            request.getSession().invalidate();
            request.getSession().setAttribute("errorMessage", messageError);
            response.sendRedirect("register.jsp");
        }
    }
}