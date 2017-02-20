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
 * Created by dima on 18.02.17.
 */
public class ConstructorResult extends HttpServlet {

    String carParmReq ="Select c "+
            "From CarParametrs c "+
            "Where c.modelName.modelName = :model "+
            " AND c.engine.nameOfEngine = :engine " +
            "AND c.kindOfBody.nameKindOfBody = :kindOfBody "+
            "AND c.transmission.transmissionName =:transmission "+
            "AND c.colorSet.id =:colorSetId ";

    String userName = null;
    String password = null;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException {

        Cookie[] cookies = request.getCookies();

        //из cookie беру логин пароль, если оно там есть, то ок, если нет то необходимо пройти процедуру аутентификации

        if (isLoggined(cookies)){

            //здесь я из request.getParametrs перевожу в request.getSession.getParametrs

            String colorset = request.getParameter("colorset");
            request.getSession().setAttribute("colorset",colorset);

            String kindOfBody = request.getSession().getAttribute("kindOfBody").toString();
            String modelName = request.getSession().getAttribute("modelName").toString();
            String engineName = request.getSession().getAttribute("engineName").toString();
            String transmission = request.getSession().getAttribute("transmission").toString();

            Session session = null;
            List objects  =  null;
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery(carParmReq)
                        .setString("model",modelName)
                        .setString("engine",engineName)
                        .setString("kindOfBody",kindOfBody)
                        .setString("transmission",transmission)
                        .setInteger("colorSetId",Integer.parseInt(colorset));
                objects = query.list();
                request.getSession().setAttribute("result",objects);
                response.sendRedirect("Result.jsp");
            }catch (Exception e) {
                //todo в случае ошибки надо выводить сообщение пользователю
                //response.sendRedirect("Engine.jsp");
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
