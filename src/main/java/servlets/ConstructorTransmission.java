package servlets;

import com.api.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dima on 14.02.17.
 */
public class ConstructorTransmission extends HttpServlet {

    String bodyReq ="Select tr "+
            "From CarParametrs c "+
            "INNER JOIN c.transmission tr " +
            "Where c.modelName.modelName = :model "+
            " AND c.engine.nameOfEngine = :engine " +
            "AND c.kindOfBody.nameKindOfBody = :kindOfBody";

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException {
        /*
        здесь я из request.getParametrs перевожу в request.getSession.getParametrs
         */
        String kindOfBody = request.getParameter("kindOfBody");
        request.getSession().setAttribute("kindOfBody",kindOfBody);

        String modelName = request.getSession().getAttribute("modelName").toString();
        String engineName = request.getSession().getAttribute("engineName").toString();


        Session session = null;
        List objects  =  null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(bodyReq)
                    .setString("model",modelName)
                    .setString("engine",engineName)
                    .setString("kindOfBody",kindOfBody);
            objects = query.list();
            session.getTransaction().commit();
            request.getSession().setAttribute("transmission",objects);
        }catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        response.sendRedirect("Transmission.jsp");

    }
}
