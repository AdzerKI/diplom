package ru.kranbe.web.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.kranbe.domain.Status;
import ru.kranbe.domain.user.Role;
import ru.kranbe.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtEntityFactory {

    public static JwtEntity create(final User user) {
        return new JwtEntity(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(
            final List<Role> roles
    ) {
        return roles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }

}
