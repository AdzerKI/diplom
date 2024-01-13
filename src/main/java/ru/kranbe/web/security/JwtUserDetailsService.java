package ru.kranbe.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kranbe.domain.user.User;
import ru.kranbe.service.user.UserService;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        User user = userService.getByUsername(username);
        return JwtEntityFactory.create(user);
    }

    public UserDetails loadUserByEmail(final String email) {
        User user = userService.getByEmail(email);
        return JwtEntityFactory.create(user);
    }

    public UserDetails loadUserByPhone(final String phone) {
        User user = userService.getByPhone(phone);
        return JwtEntityFactory.create(user);
    }
}
