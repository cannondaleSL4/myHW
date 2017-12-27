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
 * Created by dima on 13.02.17.
 */
public class ConstuctorEngine extends HttpServlet {

    String engineReq ="Select e "+
            "From CarParametrs c "+
            "INNER JOIN c.engine e " +
            "Where c.modelName.modelName = :model ";

    @Override
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException {

        String modelName = request.getParameter("modelName");
        request.getSession().setAttribute("modelName",modelName);

        Cookie[] cookies = request.getCookies();

        if (UtilLoggined.isLoggined(cookies)){
            Session session = null;
            List objects  =  null;
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery(engineReq)
                        .setString("model",modelName);
                objects = query.list();
                request.getSession().setAttribute("engineList",objects);
                response.sendRedirect("/user/Constructor/Engine.jsp");
            }catch (Exception e) {
                //todo в случае ошибки надо выводить сообщение пользователю
                response.sendRedirect("/logout");
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }else{
            response.sendRedirect("/logout");
        }
    }
}
