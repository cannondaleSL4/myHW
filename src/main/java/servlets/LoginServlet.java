package servlets;

import com.api.Factory;
import com.authentification.userEntity.Role;

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


    private final String messageErrorEmpty ="Sorry login or password is empty, try again";
    private final String messageErrorLP = "Sorry, you are wrong, login or password is" +
            " incorrect(or you need to register)";
    private final String messageError ="Sorry some error try again later";

    @Override
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

                    Role userRole = Factory.getInstance().getUserDAO().getRole(userName);

                    Cookie name = new Cookie("userName",userName);
                    response.addCookie(name);

                    Cookie pass = new Cookie("password", password);
                    response.addCookie(pass);

                    Cookie role  = new Cookie("userRole",userRole.toString());
                    response.addCookie(role);

                    request.getSession().setAttribute("userName",userName);
                    request.getSession().setAttribute("modelList",modelList);
                    request.getSession().setAttribute("userRole",userRole);

                    if (userRole.toString().equalsIgnoreCase("administrator")){
                        response.sendRedirect("/admin/user");
                    }else if (userRole.toString().equalsIgnoreCase("staff")){
                        response.sendRedirect("/staff/staffmodel");
                    }else{
                        response.sendRedirect("/user/Constructor/model");
                    }
                }else{
                    request.setAttribute("errorMessage", messageErrorLP);
                    RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                }
            } catch (SQLException e) {
                request.setAttribute("errorMessage", messageError);
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        }else{
            request.setAttribute("errorMessage", messageErrorEmpty);
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }
}