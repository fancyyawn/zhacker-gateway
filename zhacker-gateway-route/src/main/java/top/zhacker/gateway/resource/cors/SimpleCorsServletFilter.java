package top.zhacker.gateway.resource.cors;

import com.google.common.base.Strings;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * http://www.itkeyword.com/doc/4551284941349156x635/standalone-spring-oauth2-jwt-authorization-server-cors
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsServletFilter implements Filter {

    public SimpleCorsServletFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type, source");
        
        
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            if(Strings.isNullOrEmpty(response.getHeader("Access-Control-Allow-Origin"))){
                response.setHeader("Access-Control-Allow-Origin", "*");
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}