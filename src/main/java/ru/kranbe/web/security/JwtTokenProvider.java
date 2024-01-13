package ru.kranbe.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kranbe.domain.exception.AccessDeniedException;
import ru.kranbe.domain.user.Role;
import ru.kranbe.domain.user.User;
import ru.kranbe.service.user.UserService;
import ru.kranbe.service.properties.JwtProperties;
import ru.kranbe.web.dto.user.auth.JwtRefreshRequest;
import ru.kranbe.web.dto.user.auth.JwtResponse;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(final Long userId,
                                    final String username,
                                    final Set<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", userId);
        claims.put("roles", resolveRoles(roles));
        Instant validity = Instant.now()
                .plus(jwtProperties.getAccess(), ChronoUnit.HOURS);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(validity))
                .signWith(key)
                .compact();
    }

    private Set<String> resolveRoles(final Set<Role> roles) {
        Set<String> result = new HashSet<>();

        roles.forEach(role -> {
            result.add(role.getName());
        });

        return result;
    }

    public String createRefreshToken(final Long userId, final String username) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", userId);
        Instant validity = Instant.now()
                .plus(jwtProperties.getRefresh(), ChronoUnit.DAYS);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(validity))
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshUserTokens(final JwtRefreshRequest request) {
        JwtResponse response = new JwtResponse();

        if (!validateToken(request.getToken())) {
            throw new AccessDeniedException();
        }

        Long userId = Long.valueOf(getId(request.getToken()));
        User user = userService.getById(userId);
        response.setId(userId);
        response.setUsername(user.getUsername());
        response.setAccessToken(
                createAccessToken(userId, user.getUsername(), user.getRoles())
        );
        response.setRefreshToken(
                createRefreshToken(userId, user.getUsername())
        );

        return response;
    }

    public boolean validateToken(final String token) {
        Jws<Claims> claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    private String getId(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id")
                .toString();
    }

    private String getUsername(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(final String token) {
        String username = getUsername(token);
        UserDetails userDetails
                = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails,
                "",
                userDetails.getAuthorities());
    }

}
