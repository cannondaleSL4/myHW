package servlets.Authentification;


import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dima on 05.02.17.
 */
public class AuthFilter implements Filter{

    private List<String> pathFilters = Arrays.asList(
            new String [] {} );

    public AuthFilter(){

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
