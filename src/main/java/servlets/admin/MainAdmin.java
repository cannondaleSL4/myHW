package servlets.admin;

import com.api.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dima on 18.02.17.
 */
public class MainAdmin extends HttpServlet {

    String userReq ="Select u "+
            "From User u";

    String userName = null;
    String password = null;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException {

        //здесь я из request.getParametrs перевожу в request.getSession.getParametrs

        Cookie[] cookies = request.getCookies();

        //из cookie беру логин пароль, если оно там есть, то ок, если нет то необходимо пройти процедуру аутентификации

        if (isLoggined(cookies)){
            Session session = null;
            List objects  =  null;
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery(userReq);
                objects = query.list();
                request.getSession().setAttribute("userList",objects);
                response.sendRedirect("/admin/user");
            }catch (Exception e) {
                //todo в случае ошибки надо выводить сообщение пользователю
                response.sendRedirect("logout");
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
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
