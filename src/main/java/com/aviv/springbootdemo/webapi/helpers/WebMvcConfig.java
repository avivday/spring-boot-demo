package com.aviv.springbootdemo.webapi.helpers;

import com.aviv.springbootdemo.security.contract.ISecurityAuth;
import com.aviv.springbootdemo.service.user.contract.IUserService;
import com.aviv.springbootdemo.webapi.AppSettings;
import com.aviv.springbootdemo.webapi.security.JwtSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{

    private IUserService _userService;
    private ISecurityAuth _securityAuth;
    private AppSettings _appSettings;

    @Autowired
    public WebMvcConfig(IUserService userService, ISecurityAuth securityAuth, AppSettings appSettings) {
        this._userService = userService;
        this._securityAuth = securityAuth;
        this._appSettings = appSettings;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new JwtSecurityInterceptor(this._userService, this._securityAuth, this._appSettings))
                .addPathPatterns("/api/**");
    }
}
