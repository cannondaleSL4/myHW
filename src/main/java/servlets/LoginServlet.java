package servlets;

import com.api.Factory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

    private final String messageErrorEmpty = " <script>\n" +
            "        window.onload = function() {\n" +
            "            alert( \"Sorry login or password is empty, try again\" );\n" +
            "        };\n" +
            "    </script>";

    private final String messageErrorLP = " <script>\n" +
            "        window.onload = function() {\n" +
            "            alert( \"Sorry, you are wrong, login or password is incorrect( " +
            "            or you need to register)\" );\n" +
            "        };\n" +
            "    </script>";

    private final String messageError = " <script>\n" +
            "        window.onload = function() {\n" +
            "            alert( \"Sorry some error try again later\" );\n" +
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
                boolean inBase = Factory.getInstance().getUserDAO().check(userName,password);
                List modelList = Factory.getInstance().getModelNameDAO().getAll();
                if (inBase){

                    Cookie name = new Cookie("userName",userName);
                    Cookie pass = new Cookie("password", password);

                    response.addCookie(name);
                    response.addCookie(pass);

                    request.getSession().setAttribute("userName",userName);
                    request.getSession().setAttribute("modelList",modelList);
                    response.sendRedirect("./user/ModelNoEmployee.jsp");
                }else{
                    request.getSession().invalidate();
                    request.setAttribute("errorMessage", messageErrorLP);
                    RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.getSession().invalidate();
                request.setAttribute("errorMessage", messageError);
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        }else{
            request.getSession().invalidate();
            request.setAttribute("errorMessage", messageErrorEmpty);
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }
}
