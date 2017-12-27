package servlets.admin;

import com.api.Factory;
import com.authentification.userEntity.User;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dima on 26.12.17.
 */
public class DeleteUser extends HttpServlet {
    String userName = null;
    String password = null;
    Cookie[] cookies;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException {

        //здесь я из request.getParametrs перевожу в request.getSession.getParametrs

        cookies = request.getCookies();

        //из cookie беру логин пароль, если оно там есть, то ок, если нет то необходимо пройти процедуру аутентификации

        if (isLoggined(cookies)){
            Session session = null;
            List objects  =  null;
            try{
                response.sendRedirect("/admin/EditUser.jsp");
            }catch (Exception e) {
                //todo в случае ошибки надо выводить сообщение пользователю
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

    @Override
    public void doPost (HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

        cookies = request.getCookies();

        User user;

        if (isLoggined(cookies)){
            try{
                List<String> deleteArray = Arrays.asList(request.getParameterValues("delete"));
                if(deleteArray == null && deleteArray.size() == 0){
                    response.sendRedirect("/admin/user");
                }
                for(String nameForDelete : deleteArray){
                    user = Factory.getInstance().getUserDAO().getUser(nameForDelete);
                    Factory.getInstance().getUserDAO().delete(user);
                }
                response.sendRedirect("/admin/user");
            }catch (Exception e) {
                //todo в случае ошибки надо выводить сообщение пользователю
                response.sendRedirect("/logout");
            }
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