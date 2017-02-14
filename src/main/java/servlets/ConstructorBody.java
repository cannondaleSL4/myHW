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

        String modelName = request.getParameter("modelName");
        String engineName = request.getParameter("engineName");

        //System.out.println(engineName);

        Session session = null;
        List objects  =  null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(bodyReq)
                    .setString("model",modelName)
                    .setString("engine",engineName);
            objects = query.list();


            System.out.println(modelName);
            System.out.println(engineName);

            session.getTransaction().commit();
            /*
            теоретически в чек боксе можно было бы выбирать несоклько значений и в этом случае
            для корректного перебора принимаемого выбора нужно было бы использовать getParameterValues
            но т.к меня интересует только один выбор - я продолжаю работать с getParameteValues

            не, все не так. надо использовать не checkbox  а radiobutton!!!
             */
            request.getSession().setAttribute("kindOfBody",objects);
            //request.getSession().setAttribute("modelName",modelName);
            //request.getSession().setAttribute("engineName",engineName);
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
