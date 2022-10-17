package com.aviv.springbootdemo.webapi.security;


import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.security.contract.ISecurityAuth;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.AppSettings;
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

    private IUserService _userService;
    private ISecurityAuth _securityAuth;
    private AppSettings _appSettings;

    @Autowired
    public JwtSecurityInterceptor(IUserService userService, ISecurityAuth securityAuth, AppSettings appSettings) {
        this._userService = userService;
        this._securityAuth = securityAuth;
        this._appSettings = appSettings;
    }

    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        Authorize authorizedRoles = method.getMethodAnnotation(Authorize.class);

        if (authorizedRoles == null) return true; // route is not secured.

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        String currentUserBeanKey = "currentUser";
        Cookie[] cookies = requestServlet.getCookies();
        if(cookies == null || cookies.length == 0) throw new NotAuthorizedException("Unauthorized");

        Optional<Cookie> jwtCookie = Arrays.stream(cookies)
                .filter(cookie ->
                        cookie.getName().equalsIgnoreCase(this._appSettings.getJwtCookieName())
                )
                .findFirst();

        if (jwtCookie.isPresent()) {
            String token = jwtCookie.get().getValue();
            this._securityAuth.validateToken(token);
            String username = this._securityAuth.getUsernameFromToken(token);
            User user = this._userService.getUserByUsername(username);
            if(user == null) throw new NotAuthorizedException("Unauthorized");
            this._securityAuth.validateUserRole(user, authorizedRoles.value());
            beanFactory.registerSingleton(currentUserBeanKey, user);
        } else {
            beanFactory.destroySingleton(currentUserBeanKey);
            throw new NotAuthorizedException("Unauthorized");
        }
        return true;
    }
}