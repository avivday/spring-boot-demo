package com.aviv.springbootdemo.webapi.helpers;

import com.aviv.springbootdemo.security.jwt.contract.IJwtSecurityService;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.security.JwtSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{

    private IJwtSecurityService _jwtSecurityService;
    private IUserService _userService;

    @Autowired
    public WebMvcConfig(IJwtSecurityService _jwtSecurityService, IUserService userService) {
        this._jwtSecurityService = _jwtSecurityService;
        this._userService = userService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new JwtSecurityInterceptor(this._jwtSecurityService, this._userService));
    }
}
