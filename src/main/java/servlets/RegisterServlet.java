package servlets;

import com.api.Factory;
import com.authentification.userEntity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dima on 07.02.17.
 */
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final String messageError = "Sorry some error try again later";
    private final String messageErrorEmpty = "Sorry login or password is empty, try again";
    private final String messageSuccess = "You have successfully signed up";
    private final String messageBusy = "Sorry, this login is busy, try some another login";


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("userName").trim();
        String password = request.getParameter("password").trim();

        if (userName != null && !userName.isEmpty()
                && password!= null && !password.isEmpty()){
            try {
                boolean inBase = Factory.getInstance().getUserDAO().checkUserName(userName);
                if (!inBase){
                    Factory.getInstance().getUserDAO().add(userName,password);
                    request.setAttribute("messageSuccess", messageSuccess);
                    response.sendRedirect("/login.jsp");
                }else{
                    request.setAttribute("errorMessage", messageBusy);
                    RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
                    rd.forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", messageError);
                RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
                rd.forward(request, response);
            }
        }else{
            //request.getSession().invalidate();
            request.setAttribute("errorMessage", messageErrorEmpty);
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
            rd.forward(request, response);
        }
    }
}