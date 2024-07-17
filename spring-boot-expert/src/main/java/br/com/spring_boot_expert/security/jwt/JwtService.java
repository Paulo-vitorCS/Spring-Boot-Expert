package br.com.spring_boot_expert.security.jwt;

import br.com.spring_boot_expert.Application;
import br.com.spring_boot_expert.domain.UserCredentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    private final SignatureAlgorithm alg = Jwts.SIG.RS512;
    private final KeyPair pair = alg.keyPair().build();

    public String generateToken(UserCredentials user) {
        long exp = Long.parseLong(expiration);
        LocalDateTime dateTimeExp = LocalDateTime.now().plusMinutes(exp);
        Instant instant = dateTimeExp.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        return Jwts.builder()
                .subject(user.getLogin())
                .expiration(date)
                .signWith(pair.getPrivate(), alg)
                .compact();

    }

    // Decodificador
    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.
                parser()
                .verifyWith(pair.getPublic())
                .build().parseSignedClaims(token)
                .getPayload();
    }

    public boolean validToken(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime date = expirationDate.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(date);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserLogin(String token) throws ExpiredJwtException{
        return getClaims(token).getSubject();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        JwtService service = context.getBean(JwtService.class);
        UserCredentials user = UserCredentials.builder().login("teste").build();
        String token = service.generateToken(user);
        System.out.println("\nToken: " + token);

        boolean isTokenValid = service.validToken(token);
        System.out.println("O token está válido? " + isTokenValid);
        System.out.println(service.getUserLogin(token));

    }

}

// https://github.com/jwtk/jjwt?tab=readme-ov-file#quickstart