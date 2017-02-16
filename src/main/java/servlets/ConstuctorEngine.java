package servlets;

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
 * Created by dima on 13.02.17.
 */
public class ConstuctorEngine extends HttpServlet {

    String engineReq ="Select e "+
            "From CarParametrs c "+
            "INNER JOIN c.engine e " +
            "Where c.modelName.modelName = :model ";

    String userName = null;
    String password = null;

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException {
        /*
        здесь я из request.getParametrs перевожу в request.getSession.getParametrs
         */

        String modelName = request.getParameter("modelName");
        request.getSession().setAttribute("modelName",modelName);

        Cookie[] cookies = request.getCookies();
        
        /*
        из cookie беру логин пароль, если оно там есть, то ок, если нет то необходимо пройти процедуру аутентификации
         */
        //// TODO: 16.02.17  
        if (!isLoggined(cookies))
        
        
        if (userName!=null && password!=null){
            Session session = null;
            List objects  =  null;
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery(engineReq)
                        .setString("model",modelName);
                objects = query.list();
                session.getTransaction().commit();
                request.getSession().setAttribute("engineList",objects);
            }catch (Exception e) {
                //JOptionPane.showMessageDialog(null, e.getMessage(),"Error while gettAll operation", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
            response.sendRedirect("Engine.jsp");
        }else{
            response.sendRedirect("login.jsp");
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
