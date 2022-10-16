package com.aviv.springbootdemo.webapi.security.filters;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.security.jwt.contract.IJwtSecurityService;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotAuthorizedException;
import java.io.IOException;
import java.util.UUID;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private IJwtSecurityService _jwtSecurityService;
    private IUserService _userService;

    @Autowired
    public JwtAuthenticationFilter(IJwtSecurityService jwtSecurityService, IUserService userService) {
        this._jwtSecurityService = jwtSecurityService;
        this._userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this._getJwtFromRequest(request);
        try {
            this._jwtSecurityService.validateToken(token);
            UUID userUid = this._jwtSecurityService.getUserUidFromToken(token);
            User user = this._userService.getUserByUUID(userUid);
        } catch (Exception e) {
            throw new NotAuthorizedException(e);
        }

        filterChain.doFilter(request, response);
    }

    private String _getJwtFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        // change to real implementation
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjExNTE2OTM5MDIyfQ.skKXS5SL4gbADXGmGgqHPfGUcEo0ksX1R6gjlFIKGJI";
    }
}
