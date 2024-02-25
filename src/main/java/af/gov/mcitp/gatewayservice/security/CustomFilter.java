package af.gov.mcitp.gatewayservice.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**

Responsible to handle unauthorized response generated after keycloak enforcer and customize the response as needed.
*/
@Component
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if(httpServletResponse.getStatus() == HttpStatus.SC_FORBIDDEN) {
            httpServletResponse.getWriter().write("{\n" +
                    " \"status\": 503,\n" +
                    " \"message\": \"You are not authorized for this end-point\"\n" +
                    "}");
            httpServletResponse.setStatus(HttpStatus.SC_SERVICE_UNAVAILABLE);
            return;
        }
        
        filterChain.doFilter(servletRequest, servletResponse);
    }
}