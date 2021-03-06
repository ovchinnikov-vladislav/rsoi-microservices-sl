package rsoi.lab2.gservice.service.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import rsoi.lab2.gservice.entity.user.User;
import rsoi.lab2.gservice.exception.jwt.JwtAuthenticationException;
import rsoi.lab2.gservice.model.TokenObject;
import rsoi.lab2.gservice.model.jwt.JwtUserFactory;
import rsoi.lab2.gservice.service.SessionService;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.util.*;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private String secretKey = "d282dc035756736e54761761cc52bef78e3c473fa7de8f617c14f0e0ae7044aae8ba4b7bed7d532d4af91122e50b39a8bb99e320f72094547d7cae108e928460";

    @Autowired
    private SessionService sessionService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getJwsClaimsFromToken(token).getBody();
        UUID userId = UUID.fromString(claims.get("user_id", String.class));
        User user = sessionService.findUserById(userId);
        if (user != null) {
            UserDetails userDetails = JwtUserFactory.create(user);
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        }
        throw new JwtAuthenticationException("Token is invalid.");
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public UUID getUserIdFromToken(String token) {
        Claims claims = getJwsClaimsFromToken(token).getBody();
        return UUID.fromString(claims.get("user_id", String.class));
    }

    public boolean validateAccessToken(String token) {
        HashMap<String, String> t = new HashMap<>();
        t.put("access_token", token);
        return sessionService.validityToken(t);
    }

    public boolean validateRefreshToken(String token) {
        HashMap<String, String> t = new HashMap<>();
        t.put("refresh_token", token);
        return sessionService.validityToken(t);
    }

    private Jws<Claims> getJwsClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    }
}
