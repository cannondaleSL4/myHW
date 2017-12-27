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
 * Created by dima on 14.02.17.
 */

public class ConstructorBody extends HttpServlet {
    String bodyReq ="Select kind "+
            "From CarParametrs c "+
            "INNER JOIN c.kindOfBody kind " +
            "Where c.modelName.modelName = :model "+
            " AND c.engine.nameOfEngine = :engine";

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException {


        Cookie[] cookies = request.getCookies();

        if (UtilLoggined.isLoggined(cookies)) {

            String engineName = request.getParameter("engineName");
            request.getSession().setAttribute("engineName", engineName);

            String modelName = request.getSession().getAttribute("modelName").toString();

            Session session = null;
            List objects = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery(bodyReq)
                        .setString("model",modelName)
                        .setString("engine",engineName);
                objects = query.list();
                request.getSession().setAttribute("kindOfBody",objects);
                response.sendRedirect("/user/Constructor/KindOfBody.jsp");
            }catch (Exception e) {
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
