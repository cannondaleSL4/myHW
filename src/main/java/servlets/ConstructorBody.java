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
    String bodyReq ="Select e "+
            "From CarParametrs c "+
            "INNER JOIN c.engine e " +
            "Where c.modelName.modelName = :model ";

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException {

        String modelName = request.getParameter("modelName");
        String engineName = request.getParameter("engineName");

        Session session = null;
        List objects  =  null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(bodyReq)
                    .setString("model",modelName);
            objects = query.list();
            session.getTransaction().commit();
            request.getSession().setAttribute("engineList",objects);
            request.getSession().setAttribute("modelName",modelName);
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
