package servlets.user;

import com.api.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import servlets.UtilLoggined;

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
public class ConstructorModel extends HttpServlet {

    String modelReq ="Select m "+
            "From ModelName m";

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException {

        Cookie[] cookies = request.getCookies();

        if (UtilLoggined.isLoggined(cookies)){
            Session session = null;
            List objects  =  null;
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery(modelReq);
                objects = query.list();
                request.getSession().setAttribute("modelList",objects);
                response.sendRedirect("/user/Constructor/Model.jsp");
            }catch (Exception e) {
                //todo в случае ошибки надо выводить сообщение пользователю
                System.out.println(111111);
                response.sendRedirect("/logout");
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }else{
            System.out.println(222222222);
            response.sendRedirect("/logout");
        }
    }
}
