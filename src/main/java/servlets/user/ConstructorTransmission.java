package servlets.user;

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
 * Created by dima on 14.02.17.
 */
public class ConstructorTransmission extends HttpServlet {

    String transmissionReq ="Select tr "+
            "From CarParametrs c "+
            "INNER JOIN c.transmission tr " +
            "Where c.modelName.modelName = :model "+
            " AND c.engine.nameOfEngine = :engine " +
            "AND c.kindOfBody.nameKindOfBody = :kindOfBody";

    String userName = null;
    String password = null;


    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException {

        Cookie[] cookies = request.getCookies();

        if (isLoggined(cookies)){

            String kindOfBody = request.getParameter("kindOfBody");
            request.getSession().setAttribute("kindOfBody",kindOfBody);

            String modelName = request.getSession().getAttribute("modelName").toString();
            String engineName = request.getSession().getAttribute("engineName").toString();

            Session session = null;
            List objects  =  null;
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery(transmissionReq)
                        .setString("model",modelName)
                        .setString("engine",engineName)
                        .setString("kindOfBody",kindOfBody);
                objects = query.list();
                request.getSession().setAttribute("transmission",objects);
                response.sendRedirect("/user/Constructor/Transmission.jsp");
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
