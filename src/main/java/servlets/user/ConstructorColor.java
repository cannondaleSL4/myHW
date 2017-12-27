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
 * Created by dima on 17.02.17.
 */
public class ConstructorColor extends HttpServlet {
    String colorReq ="Select cs "+
            "From CarParametrs c "+
            "INNER JOIN c.colorSet cs " +
            "Where c.modelName.modelName = :model "+
            " AND c.engine.nameOfEngine = :engine " +
            "AND c.kindOfBody.nameKindOfBody = :kindOfBody "+
            "AND c.transmission.transmissionName =:transmission ";

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException {

        Cookie[] cookies = request.getCookies();

        if (UtilLoggined.isLoggined(cookies)){

            String transmission = request.getParameter("transmission");
            request.getSession().setAttribute("transmission",transmission);


            String kindOfBody = request.getSession().getAttribute("kindOfBody").toString();
            String modelName = request.getSession().getAttribute("modelName").toString();
            String engineName = request.getSession().getAttribute("engineName").toString();

            Session session = null;
            List objects  =  null;
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery(colorReq)
                        .setString("model",modelName)
                        .setString("engine",engineName)
                        .setString("kindOfBody",kindOfBody)
                        .setString("transmission",transmission);
                objects = query.list();
                session.getTransaction().commit();
                request.getSession().setAttribute("colorSet",objects);
                response.sendRedirect("/user/Constructor/Color.jsp");
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
