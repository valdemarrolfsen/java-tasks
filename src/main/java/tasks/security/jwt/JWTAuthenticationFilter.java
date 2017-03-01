package tasks.security.jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.core.Authentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

    static final String ORIGIN = "Origin";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        if (!((HttpServletRequest)request).getHeader(ORIGIN).isEmpty()) {
            String origin = ((HttpServletRequest)request).getHeader(ORIGIN);
            ((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", origin);
            ((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            ((HttpServletResponse)response).addHeader("Access-Control-Allow-Credentials", "true");
            ((HttpServletResponse)response).addHeader("Access-Control-Expose-Headers", "Authorization");
            ((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers",
                    ((HttpServletRequest)request).getHeader("Access-Control-Request-Headers"));
        }
        if (((HttpServletRequest)request).getMethod().equals("OPTIONS")) {
            response.getWriter().print("OK");
            response.getWriter().flush();
            return;
        }

        Authentication authentication = new TokenAuthenticationService().getAuthentication((HttpServletRequest)request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}
