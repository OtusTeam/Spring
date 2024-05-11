package ru.otus.spring.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class MyOwnFilter extends GenericFilterBean {
    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
        servletRequest.getParameterMap().put( "SpecialValue", new String[]{ "My dirty secret" } );
        filterChain.doFilter( servletRequest, servletResponse );
    }
}
