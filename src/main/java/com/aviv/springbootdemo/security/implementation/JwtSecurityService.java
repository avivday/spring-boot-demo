package com.aviv.springbootdemo.security.implementation;

import com.aviv.springbootdemo.model.user.User;
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
public class JwtSecurityService {

    private AppSettings appSettings;

    @Autowired
    public JwtSecurityService(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    public String generateToken(String userUid) {
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + this.appSettings.getJwtExpirationInMs());

        String token = Jwts.builder()
                .setSubject(userUid)
                .setIssuedAt(currentDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, this.appSettings.getJwtSecret())
                .compact();

        return token;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = this._getTokenClaims(token);
        return claims.getSubject();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.appSettings.getJwtSecret()).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new NotAuthorizedException(ex);
        }
    }

    private Claims _getTokenClaims(String token) {
        return Jwts.parser()
                .setSigningKey(this.appSettings.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}
