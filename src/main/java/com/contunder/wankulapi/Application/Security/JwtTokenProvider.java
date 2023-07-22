package com.contunder.wankulapi.Application.Security;

import com.contunder.wankulapi.Application.Exception.WankulAPIException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import static com.contunder.wankulapi.Application.Enum.MessageConstant.TOKEN_EMPTY;
import static com.contunder.wankulapi.Application.Enum.MessageConstant.TOKEN_EXPIRED;
import static com.contunder.wankulapi.Application.Enum.MessageConstant.TOKEN_INVALID;
import static com.contunder.wankulapi.Application.Enum.MessageConstant.TOKEN_UNSUPPORTED;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    public Optional<String> getEmail(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Optional.ofNullable(claims.getSubject());
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new WankulAPIException(HttpStatus.BAD_REQUEST, TOKEN_INVALID);
        } catch (ExpiredJwtException ex) {
            throw new WankulAPIException(HttpStatus.BAD_REQUEST, TOKEN_EXPIRED);
        } catch (UnsupportedJwtException ex) {
            throw new WankulAPIException(HttpStatus.BAD_REQUEST, TOKEN_UNSUPPORTED);
        } catch (IllegalArgumentException ex) {
            throw new WankulAPIException(HttpStatus.BAD_REQUEST, TOKEN_EMPTY);
        }
    }

}
