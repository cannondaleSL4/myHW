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

public class ConstructorBody extends HttpServlet {
    String bodyReq ="Select kind "+
            "From CarParametrs c "+
            "INNER JOIN c.kindOfBody kind " +
            "Where c.modelName.modelName = :model "+
            " AND c.engine.nameOfEngine = :engine";


    String userName = null;
    String password = null;



    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException {


        Cookie[] cookies = request.getCookies();

        if (isLoggined(cookies)) {

        //здесь я из request.getParametrs перевожу в request.getSession.getParametrs


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
            }catch (Exception e) {
                //JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
            System.out.println(request.getSession().getAttribute("kindOfBody"));
            response.sendRedirect("KindOfBody.jsp");
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
