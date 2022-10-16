package com.aviv.springbootdemo.webapi.security;


import com.aviv.springbootdemo.security.jwt.contract.IJwtSecurityService;
import com.aviv.springbootdemo.webapi.security.Authorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class JwtSecurityInterceptor implements HandlerInterceptor {

    private IJwtSecurityService _jwtSecurityService;

    @Autowired
    public JwtSecurityInterceptor(IJwtSecurityService _jwtSecurityService) {
        this._jwtSecurityService = _jwtSecurityService;
    }

    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception
    {
        HandlerMethod method = (HandlerMethod) handler;
        Authorize authorizedRoles = method.getMethodAnnotation(Authorize.class);
        if(authorizedRoles != null) {
            Cookie[] cookies = requestServlet.getCookies();
            Optional<Cookie> jwtCookie = Arrays.stream(cookies)
                    .filter(cookie ->
                            cookie.getName().equalsIgnoreCase("jwt")
                    )
                    .findFirst();
//            && cookie.isHttpOnly()
            int x = 5;
        }
        return true;
    }
}