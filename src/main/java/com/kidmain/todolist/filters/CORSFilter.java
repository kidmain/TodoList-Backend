package com.kidmain.todolist.filters;

import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <a href="https://medium.com/@rinatmuhamedgaliev/java-%D0%B8-spring-boot-%D0%B2-java-%D0%BC%D0%B8%D1%80%D0%B5-%D1%87%D0%B0%D1%81%D1%82%D1%8C-4-5-cors-%D1%84%D0%B8%D0%BB%D1%8C%D1%82%D1%80-d1b18a3c5c33">...</a>
 */

@Component
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "ngrok-skip-browser-warning");
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.setHeader("ngrok-skip-browser-warning", "true");
//        response.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
        if (httpRequest.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(Response.SC_OK);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
