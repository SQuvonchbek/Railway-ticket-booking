package uz.pdp.railwayticketproject.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.railwayticketproject.entity.User;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jjwt.token.time.expiration}")
    private Long expirationTime;

    @Value("${jjwt.token.key.secret}")
    private String secretKey;

    public String generateToken(final User user) {

            return Jwts.builder()
                    .subject(user.getUsername())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
                    .claim("roles", user.getRoles())
                    .signWith(key())
                    .compact();

    }

    public SecretKey key(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public Claims parseToken(final String token) {
        return Jwts
                .parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(final String token) {
        try {
            return parseToken(token)
                    .getExpiration()
                    .after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }


}
