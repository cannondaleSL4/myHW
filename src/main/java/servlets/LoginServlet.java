package servlets;

import com.api.Factory;
import com.authentification.userEntity.Role;
import com.authentification.userEntity.User;

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

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException {
        if (request.getAttribute("error")!=null){
            request.getSession().invalidate();
            request.getSession().setAttribute("errorMessage", messageErrorLP);
            response.sendRedirect("/login.jsp");
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException{
        String userName = request.getParameter("j_username").trim();
        String password = request.getParameter("j_password").trim();


        if (userName != null && !userName.isEmpty()
                && password!= null && !password.isEmpty()){
            try {
                boolean inBase = Factory.getInstance().getUserDAO().check(userName,password);
                if (inBase){
                    List modelList = Factory.getInstance().getModelNameDAO().getAll();
                    Cookie name = new Cookie("userName",userName);
                    response.addCookie(name);

                    Cookie pass = new Cookie("password", password);
                    response.addCookie(pass);

                    request.getSession().setAttribute("userName",userName);
                    request.getSession().setAttribute("modelList",modelList);

                    User user = Factory.getInstance().getUserDAO().getUserByNameAndPassword(userName,password);

                    if(user.getType() == Role.ADMINISTRATOR){
                        response.sendRedirect("/admin/admin.jsp");
                    }else if (user.getType() == Role.STAFF){
                        response.sendRedirect("/stuff/Constructor/model");
                    } else {
                        response.sendRedirect("/user/Constructor/model");
                    }

                }else{
                    request.getSession().invalidate();
                    request.getSession().setAttribute("errorMessage", messageErrorLP);
                    response.sendRedirect("/login.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.getSession().invalidate();
                request.getSession().setAttribute("errorMessage", messageError);
                response.sendRedirect("/login.jsp");
            }
        }else{
            request.getSession().invalidate();
            request.getSession().setAttribute("errorMessage", messageErrorEmpty);
            response.sendRedirect("/login.jsp");
        }
    }
}
