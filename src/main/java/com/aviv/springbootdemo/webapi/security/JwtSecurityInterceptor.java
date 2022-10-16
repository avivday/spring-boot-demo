package com.aviv.springbootdemo.webapi.security;


import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.security.jwt.contract.IJwtSecurityService;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.security.Authorize;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotAuthorizedException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class JwtSecurityInterceptor implements HandlerInterceptor {

    private IJwtSecurityService _jwtSecurityService;
    private IUserService _userService;

    @Autowired
    public JwtSecurityInterceptor(IJwtSecurityService jwtSecurityService, IUserService userService) {
        this._jwtSecurityService = jwtSecurityService;
        this._userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception
    {
        HandlerMethod method = (HandlerMethod) handler;
        Authorize authorizedRoles = method.getMethodAnnotation(Authorize.class);
        if(authorizedRoles != null) {
            DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
            String currentUserBeanKey = "currentUser";
            Cookie[] cookies = requestServlet.getCookies();
            Optional<Cookie> jwtCookie = Arrays.stream(cookies)
                    .filter(cookie ->
                            cookie.getName().equalsIgnoreCase("jwt")
                    )
                    .findFirst();
            if(jwtCookie.isPresent()) {
                String token = jwtCookie.get().getValue();
                this._jwtSecurityService.validateToken(token);
                UUID userUid = this._jwtSecurityService.getUserUidFromToken(token);
                User user = this._userService.getUserByUUID(userUid);
                beanFactory.registerSingleton(currentUserBeanKey, user);
            } else {
                beanFactory.destroySingleton(currentUserBeanKey);
                throw new NotAuthorizedException("Unauthorized");
            }
        }
        return true;
    }
}