package com.aviv.springbootdemo.webapi.helpers.interceptor;


import javassist.NotFoundException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception
    {
        System.out.println("MINIMAL: INTERCEPTOR PREHANDLE CALLED");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        System.out.println("MINIMAL: INTERCEPTOR PREHANDLE CALLED");
    }
}