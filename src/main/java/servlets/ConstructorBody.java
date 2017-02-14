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
public class ConstructorBody extends HttpServlet {
    String bodyReq ="Select kind "+
            "From CarParametrs c "+
            "INNER JOIN c.kindOfBody kind " +
            "Where c.modelName.modelName = :model "+
            " AND c.engine.nameOfEngine = :engine";

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException {
        /*
        здесь я из request.getParametrs перевожу в request.getSession.getParametrs
         */
        String engineName = request.getParameter("engineName");
        request.getSession().setAttribute("engineName",engineName);

        String modelName = request.getSession().getAttribute("modelName").toString();


        Session session = null;
        List objects  =  null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(bodyReq)
                    .setString("model",modelName)
                    .setString("engine",engineName);
            objects = query.list();
            session.getTransaction().commit();
            request.getSession().setAttribute("kindOfBody",objects);
        }catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        response.sendRedirect("KindOfBody.jsp");

    }
}
