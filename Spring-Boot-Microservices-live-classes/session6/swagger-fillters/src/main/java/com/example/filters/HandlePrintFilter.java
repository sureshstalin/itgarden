package com.example.filters;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

@Order(2)
public class HandlePrintFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("The filter executing  HandlePrintFilter 1 ");
//                +
//                servletRequest.getServletContext().getContextPath());
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
