package servlets;

import com.api.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
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

        if (request.getParameter("engineName")==null &&
                request.getSession().getAttribute("engineName")==null ){
            response.sendRedirect("user/Constructor/engine");
        }

        /*
        здесь я из request.getParametrs перевожу в request.getSession.getParametrs
         */

        String kindOfBody = request.getParameter("kindOfBody");
        request.getSession().setAttribute("kindOfBody",kindOfBody);

        String modelName = request.getSession().getAttribute("modelName").toString();
        String engineName = request.getSession().getAttribute("engineName").toString();


        Cookie[] cookies = request.getCookies();

        int length = cookies.length;
        String userName = null;
        String password = null;
        for(int i = 0; i <length;i++){
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("userName")){
                userName = cookie.getValue();
            }else if (cookie.getName().equals("password")){
                password = cookie.getValue();
            }
        }

        if (userName!=null && password!=null){
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
        }else{
            response.sendRedirect("login.jsp");
        }

    }
}