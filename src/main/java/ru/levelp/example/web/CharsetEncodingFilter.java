package ru.levelp.example.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CharsetEncodingFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

//        HttpServletRequest servletRequest = (HttpServletRequest) request;
//        HttpServletResponse servletResponse = (HttpServletResponse) response;
//        if (servletRequest.getSession(false).getAttribute("user") == null) {
//            servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }

        chain.doFilter(request, response);
    }
}
