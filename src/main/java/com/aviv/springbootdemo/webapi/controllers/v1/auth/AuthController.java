package com.aviv.springbootdemo.webapi.controllers.v1.auth;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.security.contract.ISecurityAuth;
import com.aviv.springbootdemo.webapi.controllers.v1.auth.models.AuthUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Authentication Controller
 */
@RestController()
@RequestMapping(
        path = "/api/v1/auth"
)
public class AuthController {

    private ISecurityAuth _securityAuth;

    public AuthController(ISecurityAuth securityAuth) {
        this._securityAuth = securityAuth;
    }

    /**
     * Authenticate
     * Implement your own authenticate, as an example only. user: user, password: 1234
     */

    @RequestMapping(path = "", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void login(AuthUser authUser, HttpServletResponse response) {
        this._securityAuth.login(authUser.getUser(), authUser.getPassword(), response);
    }
}
