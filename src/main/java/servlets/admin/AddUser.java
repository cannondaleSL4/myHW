package servlets.admin;

import com.api.Factory;
import com.api.HibernateUtil;
import com.authentification.userEntity.Role;
import com.authentification.userEntity.User;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dima on 21.02.17.
 */
public class AddUser extends HttpServlet {

    String userName = null;
    String password = null;
    Cookie[] cookies;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException {

        cookies = request.getCookies();

        if (isLoggined(cookies)) {
            try {
                response.sendRedirect("/admin/AddUser.jsp");
            } catch (Exception e) {
                response.sendRedirect("/logout");
            }
        }
    }

    @Override
    public void doPost (HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

        cookies = request.getCookies();

        String newUserName = request.getParameter("userName");
        String newUserPassword = request.getParameter("userName");
        String role = request.getParameter("role");

        User newUser;

        if (role.equals("ADMINISTRATOR")){
            newUser = new User(newUserName,newUserPassword, Role.ADMINISTRATOR);
        }else if(role.equals("STAFF")){
            newUser = new User(newUserName,newUserPassword, Role.STAFF);
        }else{
            newUser = new User(newUserName,newUserPassword, Role.USER);
        }

        Session session=null;

        if (isLoggined(cookies)) {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Factory.getInstance().getUserDAO().add(newUser);
                response.sendRedirect("/admin/user");
            } catch (Exception e) {
                response.sendRedirect("/logout");
            }
        }
    }

    private boolean isLoggined(Cookie[] cookies){
        int length = cookies.length;
        for(int i = 0; i <length;i++){
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("userName")){
                userName = cookie.getValue();
            }else if (cookie.getName().equals("password")){
                password = cookie.getValue();
            }
        }

        if (userName!=null && password!=null){
            return true;
        }
        return false;
    }
}