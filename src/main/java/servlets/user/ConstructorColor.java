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

    String userName = null;
    String password = null;


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException {

        Cookie[] cookies = request.getCookies();

        if (isLoggined(cookies)){

        //здесь я из request.getParametrs перевожу в request.getSession.getParametrs

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
                response.sendRedirect("Color.jsp");
            }catch (Exception e) {
                //JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }else{
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
