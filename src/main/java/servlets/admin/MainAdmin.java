package servlets.admin;

import com.api.Factory;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dima on 18.02.17.
 */
public class MainAdmin extends HttpServlet {

    String userName = null;
    String password = null;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException {

        Cookie[] cookies = request.getCookies();


        if (isLoggined(cookies)){
            try{
                request.getSession().setAttribute("userList", Factory.getInstance().getUserDAO().getAll());
                response.sendRedirect("/admin/admin.jsp");
            }catch (Exception e) {
                response.sendRedirect("logout");
            }
        }else{
            response.sendRedirect("logout");
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