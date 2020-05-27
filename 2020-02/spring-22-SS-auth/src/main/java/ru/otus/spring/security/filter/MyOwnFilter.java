package ru.otus.spring.security.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyOwnFilter extends GenericFilterBean {
    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
        servletRequest.getParameterMap().put( "SpecialValue", new String[]{ "My dirty secret" } );
        filterChain.doFilter( servletRequest, servletResponse );
    }
}
