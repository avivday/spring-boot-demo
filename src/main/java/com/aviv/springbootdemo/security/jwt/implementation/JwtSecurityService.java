package com.aviv.springbootdemo.security.jwt.implementation;

import com.aviv.springbootdemo.model.user.User;
import com.aviv.springbootdemo.security.jwt.contract.IJwtSecurityService;
import com.aviv.springbootdemo.webapi.AppSettings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotAuthorizedException;
import java.util.Date;
import java.util.UUID;

@Service
@Primary
@ConditionalOnProperty(prefix = "app.settings.security.jwt", name = "mock", havingValue = "false")
public class JwtSecurityService implements IJwtSecurityService {

    private AppSettings appSettings;
    private User _currentUser;

    @Autowired
    public JwtSecurityService(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    public String generateToken(String subject) {
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + this.appSettings.getJwtExpirationInMs());

        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(currentDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512,this.appSettings.getJwtSecret())
                .compact();

        return token;
    }

    public UUID getUserUidFromToken(String token) {
        Claims claims = this._getTokenClaims(token);
        return UUID.fromString(claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.appSettings.getJwtSecret()).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new NotAuthorizedException(ex);
        }

        return true;
    }


    private Claims _getTokenClaims(String token) {
        return Jwts.parser()
                .setSigningKey(this.appSettings.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}
