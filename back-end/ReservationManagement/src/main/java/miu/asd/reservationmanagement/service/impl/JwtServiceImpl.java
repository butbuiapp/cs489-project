package miu.asd.reservationmanagement.service.impl;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import miu.asd.reservationmanagement.common.Constant;
import miu.asd.reservationmanagement.common.RoleEnum;
import miu.asd.reservationmanagement.service.JwtService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    public static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @Override
    public String generateToken(String phoneNumber, String fullName, RoleEnum role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("name", fullName);
        return createToken(claims, phoneNumber);
    }

    private String createToken(Map<String, Object> claims, String phoneNumber) {
        return Jwts.builder()
                .setIssuer("Nail Shop")
                .setClaims(claims)
                .setSubject(phoneNumber)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constant.TOKEN_EXPIRATION_DURATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    public String extractUsername(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
}
