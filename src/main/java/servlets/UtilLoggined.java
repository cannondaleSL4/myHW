package servlets;

import javax.servlet.http.Cookie;

/**
 * Created by dima on 27.12.17.
 */
public class UtilLoggined {
    public static boolean isLoggined(Cookie[] cookies){
        String userName = null;
        String password = null;
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
